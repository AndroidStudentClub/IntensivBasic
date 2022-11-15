package ru.androidschool.intensivbasic

enum class Meals {
    BREAKFAST,BRUNCH,LUNCH,DINNER
}
fun getNameForMeals(meal: Meals): Int {
    return when (meal) {
        Meals.BREAKFAST -> R.string.breakfast
        Meals.BRUNCH -> R.string.brunch
        Meals.LUNCH -> R.string.lunch
        Meals.DINNER -> R.string.dinner

    }
}

enum class Dishes {
    SOUP,FIRST_COURSE,MAIN_COURSE,MEAT_DISH,SNACK, DESSERT, DRINK
}