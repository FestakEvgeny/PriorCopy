package fetskovich.evgeny.data.database

import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver

actual class DatabaseDriverFactory {

    actual fun createDriver() : SqlDriver {
        return NativeSqliteDriver(
            schema = AppDatabase.Schema,
            name = "appDatabase.db",
        )
    }
}