package com.example.myapplication.dao;


import com.example.myapplication.ZgwApplication;
import com.example.myapplication.bean.AddadsressGDBean;


import java.util.List;

/**
 * Created by Administrator on 2019/1/23.
 */

public class AddAdressHelper {
    private static AddAdressHelper sLoginHelper;
    private final AddadsressGDBeanDao mDaoDao;

    private AddAdressHelper(){
        //数据库初始化
        DaoMaster.DevOpenHelper openHelper = new DaoMaster.DevOpenHelper(ZgwApplication.getContext(),"hittory.db");
        //获取可读数据库
        DaoMaster daoMaster = new DaoMaster(openHelper.getWritableDatabase());
        //获取表管理器
        DaoSession daoSession = daoMaster.newSession();
        //获取我们要操作表的工具类；
        mDaoDao=daoSession.getAddadsressGDBeanDao();
    }
    public static AddAdressHelper getInstance() {
        if (sLoginHelper == null) {
            synchronized (AddAdressHelper.class) {
                if (sLoginHelper == null) {
                    sLoginHelper = new AddAdressHelper();
                }
            }
        }

        return sLoginHelper;
    }
    public void insert(AddadsressGDBean seclectorBean){
        mDaoDao.insert(seclectorBean);
    }

    public void delete(AddadsressGDBean seclectorBean){
        mDaoDao.delete(seclectorBean);
    }

    public List<AddadsressGDBean> query(){
        return  mDaoDao.queryBuilder().list();
    }


    public boolean queryLikeId(String search) {
        List<AddadsressGDBean> list = mDaoDao.queryBuilder().where(AddadsressGDBeanDao.Properties.Name.eq(search)).list();
        if(list.size()==0){
            return  true;
        }
        return false;
    }

    public void deleteAll(){

        mDaoDao.deleteAll();
    }

    public void insertAll(List<AddadsressGDBean> likeBeans){
        mDaoDao.insertInTx(likeBeans);
    }

    public  void update(AddadsressGDBean student){
        mDaoDao.update(student);
    }
}
