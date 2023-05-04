package com.example.myapplication.models

data class BillModel(
    var billId:String?= null,
    var billType:String?= null,
    var billAmount:String?= null,
    var billDate:String? = null,
    var billComment:String?= null
)
