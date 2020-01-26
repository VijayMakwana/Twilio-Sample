package com.twiliosample.ui.contacts.sendmessage


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.twiliosample.R
import com.twiliosample.databinding.FragmentSendMessageBinding
import com.twiliosample.util.Constants
import com.twiliosample.util.Resource
import isNetworkConnected
import show
import toast
import java.util.*


/**
 * A simple [Fragment] subclass.
 */
class SendMessageFragment : Fragment() {

    private lateinit var mBinding: FragmentSendMessageBinding
    private lateinit var mViewModel: SendMessageViewModel
    private val mNavController by lazy { Navigation.findNavController(mBinding.root) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = ViewModelProviders.of(this).get(SendMessageViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_send_message, container, false)
        mBinding.lifecycleOwner = viewLifecycleOwner
        return mBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // setup toolbar
        mBinding.layoutToolbar.imageBack?.run {
            this.show()
            this.setOnClickListener { mNavController.navigateUp() }
        }

        if (context?.isNetworkConnected() == false) {
            mBinding.textOffline.show()
            return
        }

        arguments?.let { args ->
            val contactNumber = SendMessageFragmentArgs.fromBundle(args).contactNumber
            contactNumber?.let { cnt ->
                val otp = Random().nextInt(999999)
                mBinding.etMessage.setText("Hi, Your OTP is: $otp")
                // setup clicks
                mBinding.btnSend.setOnClickListener {
                    if (mBinding.etMessage.text.toString().trim().isEmpty()) {
                        mBinding.etMessage.error = "Please enter message"
                        return@setOnClickListener
                    }
                    sendOtpMessage(contactNumber)
                }
            }
        }
    }

    // send otp message to the contact number
    private fun sendOtpMessage(contactNumber: String) {
        val from = Constants.FREE_TRIAL_NUMBER

        val liveData = mViewModel.sendMessage(
            from,
            contactNumber,
            mBinding.etMessage.text.toString()
        )

        liveData.observe(viewLifecycleOwner, Observer {
            when (it.state) {
                Resource.State.SUCCESS -> {
                    context?.toast(getString(R.string.message_sent_success))
                    val action =
                        SendMessageFragmentDirections.actionSendMessageFragmentToHomeFragment(true)
                    mNavController.navigate(action) // navigate to messages screen
                }
                Resource.State.ERROR -> context?.toast(it.message.orEmpty())
            }
        })

        @Suppress("UNCHECKED_CAST")
        mBinding.resource = liveData as LiveData<Resource<Any>>
    }


}
