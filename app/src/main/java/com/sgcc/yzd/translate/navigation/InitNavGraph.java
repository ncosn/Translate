package com.sgcc.yzd.translate.navigation;

import androidx.navigation.NavGraph;
import androidx.navigation.NavGraphNavigator;
import androidx.navigation.NavigatorProvider;
import androidx.navigation.fragment.FragmentNavigator;

import com.sgcc.yzd.translate.R;
import com.sgcc.yzd.translate.fragment.BillDetailInfoFragment;
import com.sgcc.yzd.translate.fragment.BillFirstInputNumberFragment;
import com.sgcc.yzd.translate.fragment.BillFirstSelectProvinceFragment;
import com.sgcc.yzd.translate.fragment.BillPrintFragment;
import com.sgcc.yzd.translate.fragment.BillUserInfoFragment;

public class InitNavGraph {
    /**
     * 账单打印页面跳转
     *
     * @param provider
     * @param fragmentNavigator
     * @return
     */
    public static NavGraph initBillPrintNavGraph(NavigatorProvider provider, FixedFragmentNavigator fragmentNavigator, FragmentNavigator navigator) {
        NavGraph navGraph = new NavGraph(new NavGraphNavigator(provider));
        //输入客户编号
        FragmentNavigator.Destination destination1 = fragmentNavigator.createDestination();
        destination1.setId(R.id.billFirstInputNumberFragment);
        destination1.setClassName(BillFirstInputNumberFragment.class.getCanonicalName());
        navGraph.addDestination(destination1);

        //输入用户所在省份
        FragmentNavigator.Destination destination2 = fragmentNavigator.createDestination();
        destination2.setId(R.id.billFirstSelectProvinceFragment);
        destination2.setClassName(BillFirstSelectProvinceFragment.class.getCanonicalName());
        navGraph.addDestination(destination2);

        //核对用户信息
        FragmentNavigator.Destination destination3 = fragmentNavigator.createDestination();
        destination3.setId(R.id.billUserInfoFragment);
        destination3.setClassName(BillUserInfoFragment.class.getCanonicalName());
        navGraph.addDestination(destination3);

        //电费账单
        FragmentNavigator.Destination destination4 = fragmentNavigator.createDestination();
        destination4.setId(R.id.billDetailInfoFragment);
        destination4.setClassName(BillDetailInfoFragment.class.getCanonicalName());
        navGraph.addDestination(destination4);

        //账单打印
        FragmentNavigator.Destination destination5 = fragmentNavigator.createDestination();
        destination5.setId(R.id.billPrintFragment);
        destination5.setClassName(BillPrintFragment.class.getCanonicalName());
        navGraph.addDestination(destination5);

        navGraph.setStartDestination(destination1.getId());

        return navGraph;
    }

}
