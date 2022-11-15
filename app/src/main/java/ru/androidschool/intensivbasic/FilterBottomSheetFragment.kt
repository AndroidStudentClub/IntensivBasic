package ru.androidschool.intensivbasic

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.annotation.Nullable
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import ru.androidschool.intensivbasic.databinding.FilterFragmentBinding
import java.io.Serializable

class FilterBottomSheetFragment : BottomSheetDialogFragment() {
    private lateinit var binding: FilterFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        @Nullable container: ViewGroup?,
        @Nullable savedInstanceState: Bundle?
    ): View? {
        binding = FilterFragmentBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val applyBtn = binding.applyBtn
        applyBtn.setOnClickListener {
            this.dismiss()
        }


        val data = FilterHolder()

        val chipGroup: ChipGroup = binding.meals1Cg
        chipGroup.setOnCheckedChangeListener { group, checkedId ->
            val mealsChip: Chip = chipGroup.findViewById(checkedId)
            data.selectedMeals =
                when (mealsChip.tag.toString()) {
                    "BREAKFAST" -> Meals.BREAKFAST.toString()
                    "BRUNCH" -> Meals.BRUNCH.toString()
                    "DINNER" -> Meals.DINNER.toString()
                    "LUNCH" -> Meals.LUNCH.toString()

                    else -> {
                        ""
                    }
                }

            setFragmentResult("filterChanged", bundleOf("bundleKey" to data))
        }

        val chipGroupDCG: ChipGroup = binding.dishes1Cg
        chipGroupDCG.setOnCheckedChangeListener { group, checkedId ->
            val dishesChip: Chip =
                chipGroupDCG.findViewById(checkedId)

            data.selectedDish =
                when (dishesChip.tag.toString()) {
                    "SOUP" -> Dishes.SOUP.toString()
                    "DESSERT" -> Dishes.DESSERT.toString()
                    "DRINK" -> Dishes.DRINK.toString()
                    "FIRST_COURSE" -> Dishes.FIRST_COURSE.toString()
                    "MAIN_COURSE" -> Dishes.MAIN_COURSE.toString()
                    "MEAT_DISH" -> Dishes.MEAT_DISH.toString()
                    "SNACK" -> Dishes.SNACK.toString()

                    else -> {
                        ""
                    }
                }

            setFragmentResult(
                "filterChanged",
                bundleOf("bundleKey" to data)
            )
        }

        val countOfMeals = binding.countOfMealsSb
        data.selectedNumberOfServings = countOfMeals.progress


        val timeToCook = binding.timeToCookSb
        data.selectedTimeToCook = timeToCook.progress

        val timeToCookValueTV = binding.timeToCookValueTv

        timeToCook.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seek: SeekBar, progress: Int, fromUser: Boolean) {
                timeToCookValueTV.text = progress.toString()
            }

            override fun onStartTrackingTouch(seek: SeekBar) {
                // write custom code for progress is started
            }

            override fun onStopTrackingTouch(seek: SeekBar) {
                // write custom code for progress is stopped
            }
        })


        val calories = binding.caloriesSb
        data.selectedCountOfCalories = calories.progress

        val rating = binding.ratingSb
        data.selectedRating = rating.progress.toFloat()

        return root
    }


    companion object {
        fun newInstance(): FilterBottomSheetFragment {
            return FilterBottomSheetFragment()
        }
    }

    class FilterHolder(

        var selectedMeals: String? = null,
        var selectedDish: String? = null,
        var selectedNumberOfServings: Int = 0,
        var selectedTimeToCook: Int = 0,
        var selectedCountOfCalories: Int = 0,
        var selectedRating: Float = 0f

    ) : Serializable

}