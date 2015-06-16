/*
 * Copyright 2015 Rudson Lima
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.loujin.androidtest;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import com.loujin.fragment.HelathNewFragment;
import com.loujin.fragment.HotelFragment;
import com.loujin.fragment.MessageFragment;
import com.loujin.fragment.YaoDianFragment;

import br.liveo.interfaces.NavigationLiveoListener;
import br.liveo.navigationliveo.NavigationLiveo;

public class MainActivity extends NavigationLiveo implements NavigationLiveoListener {

    public List<String> mListNameItem;
    public List<Fragment> mListFragment;
    private Fragment mContent;
    
    private Fragment fragment1,fragment2,fragment3,fragment4,fragment5;

    @Override
    public void onUserInformation() {
        //User information here
        this.mUserName.setText("kings lou");
        this.mUserEmail.setText("loujin_007@126.com");
        this.mUserPhoto.setImageResource(R.drawable.lin);
        this.mUserBackground.setImageResource(R.drawable.bg);
    }

    @Override
    public void onInt(Bundle savedInstanceState) {
        //Creation of the list items is here

        // set listener {required}
        this.setNavigationListener(this);

        // name of the list items
        mListNameItem = new ArrayList<>();
        mListNameItem.add(0, getString(R.string.inbox));
        mListNameItem.add(1, getString(R.string.starred));
        mListNameItem.add(2, getString(R.string.sent_mail));
        mListNameItem.add(3, getString(R.string.drafts));
        mListNameItem.add(4, getString(R.string.more_markers)); //This item will be a subHeader
        mListNameItem.add(5, getString(R.string.trash));
        mListNameItem.add(6, getString(R.string.spam));

        // icons list items
        List<Integer> mListIconItem = new ArrayList<>();
        mListIconItem.add(0, R.drawable.ic_inbox_black_24dp);
        mListIconItem.add(1, 0); //Item no icon set 0
        mListIconItem.add(2, 0); //Item no icon set 0
        mListIconItem.add(3, R.drawable.ic_drafts_black_24dp);
        mListIconItem.add(4, 0); //When the item is a subHeader the value of the icon 0
        mListIconItem.add(5, R.drawable.ic_domain_black_24dp);
        mListIconItem.add(6, R.drawable.ic_group_add_black_24dp);

        //{optional} - Among the names there is some subheader, you must indicate it here
        List<Integer> mListHeaderItem = new ArrayList<>();
        mListHeaderItem.add(4);

        //{optional} - Among the names there is any item counter, you must indicate it (position) and the value here
        SparseIntArray mSparseCounterItem = new SparseIntArray(); //indicate all items that have a counter
        mSparseCounterItem.put(0, 7);
        mSparseCounterItem.put(6, 250);

        fragment1 = new FragmentMain().newInstance("首页");
        mContent = fragment1;
        fragment2 = new HelathNewFragment().newInstance("健康知识");
        fragment3 = new MessageFragment().newInstance("消息");
        fragment4 = new HotelFragment().newInstance("医院");
        fragment5 = new YaoDianFragment().newInstance("药店");
  
        //If not please use the FooterDrawer use the setFooterVisible(boolean visible) method with value false
        this.setFooterInformationDrawer(R.string.settings, R.drawable.ic_settings_black_24dp);

        this.setNavigationAdapter(mListNameItem, mListIconItem, mListHeaderItem, mSparseCounterItem);
    }
    @Override
    public void onItemClickNavigation(int position, int layoutContainerId) {
    	
    	Fragment mFragment = null ; 
        switch (position) {
        case 0:
        	Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
        	startActivity(intent);
        	return;
		case 1:
			mFragment = fragment1;
			break;
		case 2:
			mFragment = fragment2;
			break;
			
		case 3:
			mFragment = fragment3;
			break;
			
		case 5:
			mFragment = fragment4;
			break;
			
		case 6:
			mFragment = fragment5;
			break;
		default:
			break;
		}
        switchContent(mContent, mFragment, layoutContainerId);
    }
    

    public void switchContent(Fragment from, Fragment to,int layoutContainerID) {
    	Log.e("from", from.getArguments().getString("args"));
    	Log.e("to", to.getArguments().getString("args"));
    	Log.e("当前", mContent.getArguments().getString("args"));
        if (mContent != to) {
            mContent = to;
            FragmentManager mFragmentManager = getSupportFragmentManager();
            android.support.v4.app.FragmentTransaction transaction = mFragmentManager.beginTransaction();
            if (!to.isAdded()) {    // 先判断是否被add过
                transaction.hide(from).add(layoutContainerID, to).commit(); // 隐藏当前的fragment，add下一个到Activity中
            } else {
                transaction.hide(from).show(to).commit(); // 隐藏当前的fragment，显示下一个
            }
        }else{
        	 FragmentManager mFragmentManager = getSupportFragmentManager();
             mFragmentManager.beginTransaction().replace(layoutContainerID, mContent).commit();
        }
    }
    
    @Override
    public void onPrepareOptionsMenuNavigation(Menu menu, int position, boolean visible) {

        //hide the menu when the navigation is opens
        switch (position) {
            case 0:
                menu.findItem(R.id.menu_add).setVisible(!visible);
                menu.findItem(R.id.menu_search).setVisible(!visible);
                break;

            case 1:
                menu.findItem(R.id.menu_add).setVisible(!visible);
                menu.findItem(R.id.menu_search).setVisible(!visible);
                break;
        }
    }

    @Override
    public void onClickUserPhotoNavigation(View v) {
        //user photo onClic
        Toast.makeText(this, R.string.open_user_profile, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClickFooterItemNavigation(View v) {
        //footer onClick
        startActivity(new Intent(this, SettingsActivity.class));
    }
}
