package com.example.myapplication.apipackage

import com.google.gson.annotations.SerializedName
data class Item(val id: Int, val name: String, val cost: Float, val descrip: String)
data class Customer(val id: Int, val phone: String, val name: String, val password: String, val birthday: String, val visited: Int, val credits: Int)
data class Employee(val id: Int, val password: String, val name: String, val wage: Float, val role: String, val time: String)
data class Order(val id: Int, val tableNum: Int, val entree: String, val side: String, val drink: String, val orderTotal: Float)
data class Ingredient(val id: Int, var food: String, var amount: Int)
data class ResponseBase(@SerializedName("error") val error: Boolean, val message: String)
data class ResponseItem(@SerializedName("error") val error: Boolean, val message: String, val item: Item)
data class ResponseItems(@SerializedName("error") val error: Boolean, val message: String, val items: List<Item>)
data class ResponseCustomer(@SerializedName("error") val error: Boolean, val message: String, val customer: Customer)
data class ResponseCustomers(@SerializedName("error") val error: Boolean, val message: String, val customers: List<Customer>)
data class ResponseEmployee(@SerializedName("error") val error: Boolean, val message: String, val employee: Employee)
data class ResponseEmployees(@SerializedName("error") val error: Boolean, val message: String, val employees: List<Employee>)
data class ResponseOrder(@SerializedName("error") val error: Boolean, val message: String, val order: Order)
data class ResponseOrders(@SerializedName("error") val error: Boolean, val message: String, val orders: List<Order>)
data class ResponseIngredient(@SerializedName("error") val error: Boolean, val message: String, val ingredient: Ingredient)
data class ResponseIngredients(@SerializedName("error") val error: Boolean, val message: String, val Ingredients: List<Ingredient>)