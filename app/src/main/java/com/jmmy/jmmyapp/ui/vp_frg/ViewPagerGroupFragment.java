package com.jmmy.jmmyapp.ui.vp_frg;

import androidx.fragment.app.Fragment;

import com.jmmy.jmmyapp.ui.base.fragment.BasePagerFragment;
import com.jmmy.jmmyapp.ui.tab_bar.fragment.TabBar1Fragment;
import com.jmmy.jmmyapp.ui.tab_bar.fragment.TabBar2Fragment;
import com.jmmy.jmmyapp.ui.tab_bar.fragment.TabBar3Fragment;
import com.jmmy.jmmyapp.ui.tab_bar.fragment.TabBar4Fragment;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerGroupFragment extends BasePagerFragment {
    @Override
    protected List<Fragment> pagerFragment() {
        List<Fragment> list = new ArrayList<>();
        list.add(new TabBar1Fragment());
        list.add(new TabBar2Fragment());
        list.add(new TabBar3Fragment());
        list.add(new TabBar4Fragment());
        return list;
    }

    @Override
    protected List<String> pagerTitleString() {
        List<String> list = new ArrayList<>();
        list.add("page1");
        list.add("page2");
        list.add("page3");
        list.add("page4");
        return list;
    }
}
