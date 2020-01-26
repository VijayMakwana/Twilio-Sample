package com.twiliosample.ui.contacts


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.twiliosample.R
import com.twiliosample.databinding.FragmentContactDetailsBinding
import show

/**
 * A simple [Fragment] subclass.
 */
class ContactDetailsFragment : Fragment() {

    private lateinit var mBinding: FragmentContactDetailsBinding
    private val mNavController by lazy { Navigation.findNavController(mBinding.root) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_contact_details, container, false)
        return mBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // setup toolbar
        mBinding.layoutToolbar?.imageBack?.run {
            this.show()
            this.setOnClickListener { mNavController.navigateUp() }
        }

        // get the contact data
        arguments?.let { args ->
            val contact = ContactDetailsFragmentArgs.fromBundle(args).contact
            contact?.let { cnt ->
                mBinding.contact = cnt
                // setup clicks
                mBinding.btnSendMessage.setOnClickListener {
                    val action =
                        ContactDetailsFragmentDirections.actionContactDetailsFragmentToSendMessageFragment(
                            cnt.phoneNumber
                        )
                    mNavController.navigate(action)
                }
            }
        }
    }


}
