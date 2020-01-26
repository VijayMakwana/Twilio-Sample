package com.twiliosample.ui.messages


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.nitrico.lastadapter.LastAdapter
import com.twiliosample.R
import com.twiliosample.databinding.FragmentMessagesBinding
import com.twiliosample.model.MessageListResponse
import com.twiliosample.util.Resource
import isNetworkConnected
import show
import toast

/**
 * A simple [Fragment] subclass.
 */
class MessagesFragment : Fragment() {

    companion object {
        fun newInstance() = MessagesFragment()
    }

    private lateinit var mBinding: FragmentMessagesBinding
    private lateinit var mViewModel: MessagesViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = ViewModelProviders.of(this).get(MessagesViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_messages, container, false)
        mBinding.lifecycleOwner = viewLifecycleOwner
        return mBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (context?.isNetworkConnected() == false) {
            mBinding.textOffline.show()
            return
        }

        setupMessageList()
    }

    private fun setupMessageList() {
        // add divider in between items
        mBinding.messageList.addItemDecoration(
            DividerItemDecoration(
                context,
                LinearLayoutManager.VERTICAL
            )
        )

        val liveData = mViewModel.getMessagesList()
        liveData.observe(viewLifecycleOwner, Observer {
            when (it.state) {
                Resource.State.SUCCESS -> {
                    setupAdapter(it.data?.messages)
                }
                Resource.State.ERROR -> context?.toast(getString(R.string.message_something_wrong))
            }
        })

        @Suppress("UNCHECKED_CAST")
        mBinding.resource = liveData as LiveData<Resource<Any>>
    }

    // set recycler-view adapter
    private fun setupAdapter(messages: List<MessageListResponse.Message>?) {
        if (messages.isNullOrEmpty()) {
            mBinding.layoutEmptyView.root.show()
        } else {
            LastAdapter(messages.orEmpty())
                .map<MessageListResponse.Message>(
                    R.layout.item_message,
                    com.twiliosample.BR.message
                )
                .into(mBinding.messageList)
        }
    }


}
