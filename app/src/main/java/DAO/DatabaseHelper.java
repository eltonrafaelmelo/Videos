package DAO;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import Model.DetailMovieModel;

/**
 * Created by elton.melo on 05/05/2016.
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME = "mybanco.sqlite";
    private static final int DATABASE_VERSION = 3;

    private Dao<DetailMovieModel, Integer> detailMovieModel = null;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {

        try {
            Log.i(DatabaseHelper.class.getName(), "CRIADO BANCO DE DADOS");
            TableUtils.createTable(connectionSource, DetailMovieModel.class);
        } catch (SQLException e) {
            Log.e(DatabaseHelper.class.getName(), "NAO CRIADO BANCO DE DADOS", e);
            throw new RuntimeException(e);
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            Log.i(DatabaseHelper.class.getName(), "onUpgrade");
            TableUtils.dropTable(connectionSource, DetailMovieModel.class, true);
            // after we drop the old databases, we create the new ones
            onCreate(database, connectionSource);
        } catch (SQLException e) {
            Log.e(DatabaseHelper.class.getName(), "Can't drop databases", e);
            throw new RuntimeException(e);
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
    }

    public Dao<DetailMovieModel, Integer> getBookDao() {
        if ( detailMovieModel == null) {
            try {
                detailMovieModel = getDao(DetailMovieModel.class);
            } catch (java.sql.SQLException e) {
                e.printStackTrace();
            }
        }
        return detailMovieModel;
    }

    @Override
    public void close() {
        super.close();
        detailMovieModel = null;
    }

}
