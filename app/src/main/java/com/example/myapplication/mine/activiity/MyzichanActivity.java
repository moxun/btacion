package com.example.myapplication.mine.activiity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.base.BaseActivity;
import com.example.myapplication.mine.adapter.HeyueAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyzichanActivity extends BaseActivity {

    @BindView(R.id.finish)
    ImageButton finish;
    @BindView(R.id.tool_title)
    TextView toolTitle;
    @BindView(R.id.zcgz)
    TextView zcgz;
    @BindView(R.id.zc_size)
    TextView zcSize;
    @BindView(R.id.zc_type)
    TextView zcType;
    @BindView(R.id.open_img)
    ImageView openImg;
    @BindView(R.id.ll_chongbi)
    LinearLayout llChongbi;
    @BindView(R.id.ll_tibi)
    LinearLayout llTibi;
    @BindView(R.id.bb_size)
    TextView bbSize;
    @BindView(R.id.search)
    ImageView search;
    @BindView(R.id.edit_name)
    EditText editName;
    @BindView(R.id.seek_img)
    ImageView seekImg;
    @BindView(R.id.recy_heyue)
    RecyclerView recyHeyue;

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        toolTitle.setText(getString(R.string.mymoney));
        ArrayList<String> strings = new ArrayList<>();
        strings.add("55"); strings.add("55"); strings.add("55"); strings.add("55"); strings.add("55");
        HeyueAdapter heyueAdapter = new HeyueAdapter(strings);
        recyHeyue.setLayoutManager(new LinearLayoutManager(this));
        recyHeyue.setAdapter(heyueAdapter);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_myzichan;
    }




    @OnClick({R.id.finish, R.id.open_img, R.id.ll_chongbi, R.id.ll_tibi, R.id.search, R.id.seek_img})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.finish:
                finish();
                break;
            case R.id.open_img:
                break;
            case R.id.ll_chongbi:
                break;
            case R.id.ll_tibi:
                break;
            case R.id.search:
                break;
            case R.id.seek_img:
                break;
        }
    }
}
