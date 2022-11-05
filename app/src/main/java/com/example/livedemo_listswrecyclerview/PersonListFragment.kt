package com.example.livedemo_listswrecyclerview

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

private const val TAG = "PersonListFragment"

class PersonListFragment : Fragment()
{
    private lateinit var personRecyclerView: RecyclerView
    private var adapter: PersonAdapter? = null

    private val myViewModel: MyViewModel by lazy {
        ViewModelProviders.of(this).get(MyViewModel::class.java)
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.v(TAG, "onCreate() - Total people: ${myViewModel.people.size}")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragement_person_list, container, false)

        this.personRecyclerView = view.findViewById(R.id.person_recycler_view)
        this.personRecyclerView.layoutManager = LinearLayoutManager(this.context)

        this.updateUI()

        return view
    }

    private fun updateUI()
    {
        val people = this.myViewModel.people
        this.adapter = PersonAdapter(people)
        this.personRecyclerView.adapter = adapter
    }

    companion object {
        fun newInstance(): PersonListFragment {
            return PersonListFragment()
        }
    }

    private inner class PersonHolder(view: View)
        : RecyclerView.ViewHolder(view)
    {
        private lateinit var person: Person

        val nameTextView: TextView = this.itemView.findViewById(R.id.list_item_person_name)
        val ageTextView: TextView = this.itemView.findViewById(R.id.list_item_person_age)
        val coolCheck: CheckBox = this.itemView.findViewById(R.id.list_item_person_cool)

        fun bind(person: Person)
        {
            this.person = person

            nameTextView.text = this.person.name
            ageTextView.text = this.person.age.toString()
            coolCheck.isChecked = this.person.isSuperCool
        }
    }

    private inner class PersonAdapter(var people: List<Person>)
        : RecyclerView.Adapter<PersonHolder>()
    {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonHolder {

            Log.v(TAG, "onCreateViewHolder() - Creating a new view holder")

            val view = layoutInflater.inflate(R.layout.list_item_person, parent, false)

            return PersonHolder(view)
        }

        override fun onBindViewHolder(holder: PersonHolder, position: Int) {
            val person = this.people[position]

            holder.bind(person)
        }

        override fun getItemCount(): Int {
            return this.people.size
        }

    }
}