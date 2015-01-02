package com.cse.warana.dao.impl;

import com.cse.warana.dao.StoreAchievementDao;
import com.cse.warana.model.AchievementTbl;
import org.springframework.stereotype.Repository;

/**
 * Created by Nadeeshaan on 12/14/2014.
 */

@Repository("storeAchievement")
public class StoreAchievementDaoImpl extends BaseJPADaoImpl<AchievementTbl> implements StoreAchievementDao {
}
