package com.example.myapplication.mine.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.myapplication.R;
import com.example.myapplication.ZgwApplication;
import com.example.myapplication.base.BaseFragment;
import com.example.myapplication.bean.ModuleBean;
import com.example.myapplication.bean.RegisterBean;
import com.example.myapplication.bean.UserinfoBean;
import com.example.myapplication.mine.activiity.AddressActivity;
import com.example.myapplication.mine.activiity.ChongbiActivity;
import com.example.myapplication.mine.activiity.MyzichanActivity;
import com.example.myapplication.mine.activiity.SeetingsActivity;
import com.example.myapplication.mine.activiity.TibiActivity;
import com.example.myapplication.mine.activiity.UpdateNameActivity;
import com.example.myapplication.mine.adapter.ResponeAdapter;
import com.example.myapplication.okhttp.OkHttpUtils;
import com.example.myapplication.okhttp.callback.ResponseCallBack;
import com.example.myapplication.okhttp.callback.ResultModelCallback;
import com.example.myapplication.utils.SharedPreferenceUtils;
import com.example.myapplication.utils.ToastUtils;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;

import org.json.JSONException;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class MineFragment extends BaseFragment {


    @BindView(R.id.seetings)
    ImageView seetings;
    @BindView(R.id.mine_head)
    ImageView mineHead;
    @BindView(R.id.my_name)
    TextView myName;
    @BindView(R.id.my_id)
    TextView myId;

    @BindView(R.id.recy_mine_item)
    RecyclerView recyMineItem;
    @BindView(R.id.ll_chongbi)
    LinearLayout llChongbi;
    @BindView(R.id.ll_tibi)
    LinearLayout llTibi;
    @BindView(R.id.ll_mymoney)
    LinearLayout llMymoney;

    private List<ModuleBean> moduleBeans = new ArrayList<>();
    private List<ModuleBean> reponses = new ArrayList<>();
    private List<LocalMedia> selectList;
    private File file;

    public MineFragment() {
        // Required empty public constructor
    }


    @Override
    protected void initView(View view, Bundle savedInstanceState) {


        reponses.add(new ModuleBean(getString(R.string.mybill), R.drawable.icon_mybill, 0));
        reponses.add(new ModuleBean(getString(R.string.recommend), R.drawable.icon_recommend, 0));
        reponses.add(new ModuleBean(getString(R.string.notice), R.drawable.icon_notice, 0));
        reponses.add(new ModuleBean(getString(R.string.yijian), R.drawable.icon_yijian, 0));
        reponses.add(new ModuleBean(getString(R.string.aboutas), R.drawable.icon_aboutas, 0));
        recyMineItem.setLayoutManager(new LinearLayoutManager(mActivity));
        recyMineItem.setNestedScrollingEnabled(false);

        recyMineItem.setAdapter(new ResponeAdapter(reponses, getContext()));
    }


    @Override
    protected void initData() {
        getUserinfo();
    }

    private void getUserinfo() {
        OkHttpUtils.get()
                .url(ZgwApplication.appRequestUrl + "wallet/v1/user/info")

                .addHeader("X-Requested-With", "XMLHttpReques")
                .addHeader("Authorization", SharedPreferenceUtils.getToken())


                .build()
                .execute(new ResultModelCallback(mActivity, new ResponseCallBack<UserinfoBean>() {
                    @Override
                    public void onError(String e) {
                        Toast.makeText(mActivity, e, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(UserinfoBean response) throws JSONException {
                        UserinfoBean.DataBean.UserBean user = response.getData().getUser();
                        myName.setText(user.getUserName());
                        myId.setText("ID:"+user.getId());
                        RequestOptions requestOptions = RequestOptions.circleCropTransform().error(R.drawable.icon_mine_no);
                        Glide.with(mActivity)
                                .load(ZgwApplication.urlimg+user.getUserHead())
                                .apply(requestOptions)
                                .into(mineHead);
                    }
                }));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mine;
    }


    @OnClick(R.id.seetings)
    public void onViewClicked() {
        startActivity(new Intent(mActivity, SeetingsActivity.class));
    }



    @OnClick({R.id.ll_chongbi, R.id.ll_tibi, R.id.ll_mymoney, R.id.ll_address,R.id.mine_head})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_chongbi:
                startActivity(ChongbiActivity.class);
                break;
            case R.id.ll_tibi:
                startActivity(TibiActivity.class);
                break;
            case R.id.ll_mymoney:
                startActivity(MyzichanActivity.class);
                break;
            case R.id.ll_address:
                startActivity(AddressActivity.class);
                break;
            case R.id.mine_head:
                // 进入相册 以下是例子：不需要的api可以不写
                PictureSelector.create(mActivity)
                        .openGallery(PictureMimeType.ofImage())// 全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
                        .theme(R.style.picture_default_style)// 主题样式设置 具体参考 values/styles   用法：R.style.picture.white.style
                        .maxSelectNum(1)// 最大图片选择数量
                        .minSelectNum(1)// 最小选择数量
                        .imageSpanCount(4)// 每行显示个数
                        .previewImage(true)// 是否可预览图片
                        .previewVideo(false)// 是否可预览视频
                        .enablePreviewAudio(false) // 是否可播放音频
                        .isCamera(true)// 是否显示拍照按钮
                        .isZoomAnim(true)// 图片列表点击 缩放效果 默认true
                        .enableCrop(true)// 是否裁剪
                        .compress(true)// 是否压缩
                        .synOrAsy(true)//同步true或异步false 压缩 默认同步
                        .glideOverride(160, 160)// glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
                        .isGif(false)// 是否显示gif图片
                        .freeStyleCropEnabled(true)// 裁剪框是否可拖拽
                        .circleDimmedLayer(true)// 是否圆形裁剪
                        .showCropFrame(false)// 是否显示裁剪矩形边框 圆形裁剪时建议设为false
                        .showCropGrid(false)// 是否显示裁剪矩形网格 圆形裁剪时建议设为false
                        .openClickSound(false)// 是否开启点击声音
                        .minimumCompressSize(100)// 小于100kb的图片不压缩
                        .scaleEnabled(true)// 裁剪是否可放大缩小图片
                        .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code
                break;
        }
    }

    public void updateHead(File file) {
        RequestOptions requestOptions = RequestOptions.circleCropTransform().error(R.drawable.icon_mine_no);
        Glide.with(mActivity)
                .load(file)
                .apply(requestOptions)
                .into(mineHead);

        OkHttpUtils.post() .url(ZgwApplication.appRequestUrl + "wallet/v1/user/headUpload")

                .addHeader("X-Requested-With", "XMLHttpReques")
                .addHeader("Authorization", SharedPreferenceUtils.getToken())
                .addFile("file",file.getName(),file)
                .build().execute(new ResultModelCallback(mActivity, new ResponseCallBack<RegisterBean>() {
            @Override
            public void onError(String e) {

            }

            @Override
            public void onResponse(RegisterBean response) throws JSONException {
                ToastUtils.showToast(getResources().getString(R.string.update_sussec));
            }
        }));

    }
}
