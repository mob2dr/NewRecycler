package Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.recycler.Data;

@Database(entities = {Data.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static volatile AppDatabase INSTANCE;
    public abstract DataDao dataDao();

    // Home Activity -> getInstance if (INSTNACE == null => True )  if (INSTANCE == null -> TRUE) -> intsance = ....
    // Main Activity -> getInstance if (INSTNACE == null => True )  ................................................ if( INSTANCE == null -> False)
    public static AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null)
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "app.db")
                            .allowMainThreadQueries()
                            .build();
            }
        }
        return INSTANCE;
    }
}
