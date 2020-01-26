package com.twiliosample.ui.contacts


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.nitrico.lastadapter.LastAdapter
import com.google.gson.Gson
import com.twiliosample.BR
import com.twiliosample.R
import com.twiliosample.databinding.FragmentContactListBinding
import com.twiliosample.databinding.ItemContactBinding
import com.twiliosample.model.Contact
import com.twiliosample.ui.HomeFragment
import com.twiliosample.ui.HomeFragmentDirections
import show


/**
 * A simple [Fragment] subclass.
 */
class ContactListFragment : Fragment() {

    companion object {
        fun newInstance(): ContactListFragment = ContactListFragment()
    }

    private lateinit var mBinding: FragmentContactListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_contact_list, container, false)
        return mBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // setup contacts in the UI
        setupContactList()
    }


    private fun setupContactList() {
        // add divider in between items
        mBinding.contactList.addItemDecoration(
            DividerItemDecoration(
                context,
                LinearLayoutManager.VERTICAL
            )
        )

        // set adapter to the contact recycler-view
        val contacts = getContacts()
        if (contacts.isEmpty()) {
            mBinding.layoutEmptyView.root.show()
        } else {
            LastAdapter(contacts)
                .map<Contact, ItemContactBinding>(R.layout.item_contact, BR.contact) {
                    onClick {
                        val action =
                            HomeFragmentDirections.actionHomeFragmentToContactDetailsFragment(it.binding.contact)

                        if (parentFragment is HomeFragment) (parentFragment as HomeFragment).mNavController.navigate(
                            action
                        )
                    }
                }
                .into(mBinding.contactList)
        }
    }


    // parse static json string response to
    // list of contact objects
    private fun getContacts(): List<Contact> {
        // read json file first
        val inputStream = resources.openRawResource(R.raw.contacts_response)
        val response: StringBuffer = StringBuffer()

        inputStream.bufferedReader().use { input -> input.forEachLine { response.append(it) } }

        // convert from json string to list of contacts
        return Gson().fromJson<Array<Contact>>(
            response.toString(),
            Array<Contact>::class.java
        ).toList()
    }

}
