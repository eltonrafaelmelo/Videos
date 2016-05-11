package DAO;

import android.content.Context;
import android.database.SQLException;

import java.util.List;

import Model.DetailMovieModel;

/**
 * Created by elton.melo on 05/05/2016.
 */
public class DatabaseManager {

    private static DatabaseManager instance;
    private DatabaseHelper helper;

    public static DatabaseManager init(Context ctx) {
        if (null == instance) {
            instance = new DatabaseManager(ctx);
        }
        return null;
    }

    public static DatabaseManager getInstance() {
        return instance;
    }

    private DatabaseManager(Context ctx) {
        helper = new DatabaseHelper(ctx);
    }

    private DatabaseHelper getHelper() {
        return helper;
    }

    public List<DetailMovieModel> findAllDetailMovie() {
        List<DetailMovieModel> list = null;
        try {
            list = getHelper().getBookDao().queryBuilder()
                    .orderBy("id", false).query();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
