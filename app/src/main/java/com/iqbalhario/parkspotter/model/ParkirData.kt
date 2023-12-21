package com.iqbalhario.parkspotter.model

import com.google.gson.annotations.SerializedName

data class ParkirData(

	@field:SerializedName("Parkir")
	val parkir: List<ParkirItem>
)

data class ParkirItem(

	@field:SerializedName("Vehicle")
	val vehicle: String,

	@field:SerializedName("Type")
	val type: String,

	@field:SerializedName("Address")
	val address: String,

	@field:SerializedName("Price")
	val price: Int,

	@field:SerializedName("Access")
	val access: String,

	@field:SerializedName("Kapasitas ")
	val kapasitas: Int,

	@field:SerializedName("Id")
	val id: Int,

	@field:SerializedName("Day")
	val day: String,

	@field:SerializedName("Longtitude, Latitude")
	val longtitudeLatitude: String,

	@field:SerializedName("Name")
	val name: String,

	@field:SerializedName("Open")
	val open: String
)
