package com.example.client_flutter_symds

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import io.flutter.embedding.android.FlutterActivity
import org.jumpmind.symmetric.android.SQLiteOpenHelperRegistry
import org.jumpmind.symmetric.android.SymmetricService
import org.jumpmind.symmetric.common.ParameterConstants
import java.util.Properties

class MainActivity: FlutterActivity() {
    override fun onStart() {
        super.onStart()

        val symmetricdsOpenHelper = SDSDbHelper(this);

        val HELPER_KEY = "H2";
        SQLiteOpenHelperRegistry.register(HELPER_KEY, symmetricdsOpenHelper)
        val intent = Intent(this, SymmetricService::class.java)

        intent.putExtra(SymmetricService.INTENTKEY_SQLITEOPENHELPER_REGISTRY_KEY, HELPER_KEY)
        intent.putExtra(
            SymmetricService.INTENTKEY_REGISTRATION_URL,"http://10.0.2.2:31416/sync/corp-000"
        )

        val properties = Properties()

        properties.setProperty("auto.reload", "true")

        intent.putExtra(SymmetricService.INTENTKEY_PROPERTIES, properties)
        intent.putExtra(SymmetricService.INTENTKEY_EXTERNAL_ID, "android-simulator")
        intent.putExtra(SymmetricService.INTENTKEY_NODE_GROUP_ID, "store")
        intent.putExtra(SymmetricService.INTENTKEY_START_IN_BACKGROUND, true)

        this.startService(intent)
    }
}
