package ru.androidschool.intensivbasic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val filter = findViewById<ImageView>(R.id.filter_button)

        filter.setOnClickListener {
            val addPhotoBottomFragmentFragment: FilterBottomSheetFragment =
                FilterBottomSheetFragment.newInstance()

            addPhotoBottomFragmentFragment.show(
                supportFragmentManager,
                "filter_fragment"
            )
        }

        supportFragmentManager.setFragmentResultListener(
            "filterChanged",
            this
        ) { key, bundle ->
            val result: FilterBottomSheetFragment.FilterHolder =
                bundle.getSerializable("bundleKey") as FilterBottomSheetFragment.FilterHolder

            Log.d("FragmentResultListener", result.selectedDish.toString())

        }
    }
}