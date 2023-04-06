package com.mmh.zikrapp.ui

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.mmh.zikrapp.adapter.DuaAdapter
import com.mmh.zikrapp.databinding.FragmentZikrBinding
import com.mmh.zikrapp.entity.DuaItem
import org.json.JSONArray
import org.json.JSONException
import java.io.BufferedReader


class ZikrFragment : Fragment() {

    private val binding: FragmentZikrBinding by lazy {
        FragmentZikrBinding.inflate(layoutInflater)
    }
    private val duaAdapter = DuaAdapter(onItemClick = { title: String ->  increaseCount(title) })
    private var duaList = mutableListOf<DuaItem>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        (requireActivity() as AppCompatActivity).supportActionBar?.title = "Daily Zikr"
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        duaList = getDuaFromJson()
        binding.duaRv.apply {
            layoutManager = LinearLayoutManager(requireActivity())
            adapter = duaAdapter
        }
        duaAdapter.submitList(duaList)
    }

    private fun increaseCount(title: String) {
        duaList.filter { it.title == title }.forEach {
            if (it.clickedCount < it.totalCount) it.clickedCount++
        }
        val index = duaList.indexOfFirst { it.title == title }
        if (index != -1) duaAdapter.notifyItemChanged(index, Unit)
    }

    private fun getDuaFromJson(): MutableList<DuaItem> {
        val duaItems = mutableListOf<DuaItem>()
        try {
            val jsonString = requireActivity().assets.open("data.json").bufferedReader().use(BufferedReader::readText)
            val jsonArray = JSONArray(jsonString)
            for (i in 0 until jsonArray.length()) {
                val jsonObject = jsonArray.getJSONObject(i)
                val totalCount = jsonObject.getInt("count")
                val title = jsonObject.getString("title")
                val arabic = jsonObject.getString("arabic")
                val transliteration = jsonObject.getString("transliteration")
                val uzbek = jsonObject.getString("uzbek")
                val duaItem = DuaItem(
                    title = title,
                    arabic = arabic,
                    transliteration = transliteration,
                    uzbek = uzbek,
                    clickedCount = 0,
                    totalCount = totalCount
                )
                duaItems.add(duaItem)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return duaItems
    }
}