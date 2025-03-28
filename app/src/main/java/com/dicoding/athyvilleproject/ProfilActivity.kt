package com.dicoding.athyvilleproject

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.dicoding.athyvilleproject.databinding.ActivityProfilBinding

class ProfilActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityProfilBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfilBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnGithub.setOnClickListener(this)
    }

    @SuppressLint("UseKtx", "QueryPermissionsNeeded")
    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.btn_github -> {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/athyathanasia01"))
                if (intent.resolveActivity(packageManager) != null){
                    startActivity(intent)
                }
            }
        }
    }
}