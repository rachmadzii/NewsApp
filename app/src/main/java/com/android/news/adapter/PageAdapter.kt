package com.android.news.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.android.news.ui.fragment.FilmFragment
import com.android.news.ui.fragment.SportFragment
import com.android.news.ui.fragment.GameFragment
import com.android.news.ui.fragment.TechFragment

class PageAdapter(fm: FragmentManager): FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return when(position){
            0 -> TechFragment()
            1 -> GameFragment()
            2 -> FilmFragment()
            else -> {
                return SportFragment()
            }
        }
    }

    override fun getCount() = 4

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0 -> "Tech"
            1 -> "Games"
            2 -> "Film"
            else -> {
                return "Sports"
            }
        }
    }
}