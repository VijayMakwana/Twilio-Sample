package com.twiliosample.ui


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.easypageradapter.setEasyFragmentPagerAdapter
import com.twiliosample.R
import com.twiliosample.databinding.FragmentHomeBinding
import com.twiliosample.ui.contacts.ContactListFragment
import com.twiliosample.ui.messages.MessagesFragment

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {

    private lateinit var mBinding: FragmentHomeBinding

    val mNavController by lazy { Navigation.findNavController(mBinding.root) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        return mBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // setup viewpager with contacts and messages fragments
        setupViewPagers()

        arguments?.let { args ->
            val isMessageSent = HomeFragmentArgs.fromBundle(args).messageSent
            if (isMessageSent) {
                mBinding.pagerHome.currentItem = 1 // here show the messages
            }
        }
    }

    private fun setupViewPagers() {
        mBinding.pagerHome.setEasyFragmentPagerAdapter(
            childFragmentManager,
            listOf(ContactListFragment.newInstance(), MessagesFragment.newInstance())
        ).pageTitle(listOf(getString(R.string.title_contacts), getString(R.string.title_messages)))

        mBinding.tabHome.setupWithViewPager(mBinding.pagerHome)
    }
}
