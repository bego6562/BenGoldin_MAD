package com.example.lab8

data class FormShare(var name:String = "", var url:String = "") {

    fun suggestSalary(position:Int) {
        setSalary(position)
        setShare(position)
    }

    private fun setSalary(position: Int){
        when (position) {
            0 -> name="30k/year"
            1 -> name="40k/year"
            2 -> name="50k/year"
            3 -> name="60k/year"
            4 -> name="70k/year"
            5 -> name="80k/year"
            else -> name="Submitted"
        }
    }

    private fun setShare(position: Int){
        when (position) {
            0 -> url="https://creative.colorado.edu/~bego6562/MAD/30-thousand.html"
            1 -> url="https://creative.colorado.edu/~bego6562/MAD/30-thousand.html"
            2 -> url="https://creative.colorado.edu/~bego6562/MAD/30-thousand.html"
            3 -> url="https://creative.colorado.edu/~bego6562/MAD/60-thousand.html"
            4 -> url="https://creative.colorado.edu/~bego6562/MAD/60-thousand.html"
            5 -> url="https://creative.colorado.edu/~bego6562/MAD/60-thousand.html"
            else -> url="https://creative.colorado.edu/~bego6562/MAD/30-thousand.html"
        }
    }
}
