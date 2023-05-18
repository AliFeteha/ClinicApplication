package com.example.android.clinicapp.utils

import android.util.Log
import androidx.room.TypeConverter
import com.example.android.clinicapp.data.consts.*
import com.example.android.clinicapp.data.dto.DoctorsDTO
import com.example.android.clinicapp.data.dto.PatientsDTO


class TypeConverter{
    @TypeConverter
    fun fromCommentToList(value: Comment): List<String> {
        return listOf(value.body,value.name,value.id,value.image_url)
    }
    @TypeConverter
    fun fromListToComment(list:List<String>): Comment {
        return Comment(list[0],list[1],list[2],list[3])
    }
    @TypeConverter
    fun commentListToString(comments: List<Comment>): String {
        val stringBuilder = StringBuilder()
        for (comment in comments) {
            stringBuilder.append("{id=${comment.id}, name=${comment.name}, image_url=${comment.image_url}, body=${comment.body}}")
        }
        return stringBuilder.toString()
    }
    @TypeConverter
    fun stringToCommentList(string: String): List<Comment> {
        val comments: MutableList<Comment> = mutableListOf()
        val elements = string.split("}{")
        for (element in elements) {
            val trimmedString = element.trim('{', '}')
            val keyValuePairs = trimmedString.split(',')
            val values = mutableMapOf<String, String>()
            for (keyValuePair in keyValuePairs) {
                val parts = keyValuePair.split('=')
                values[parts[0].trim()] = parts[1].trim()
            }
            val comment = Comment(values["id"]!!, values["name"]!!, values["image_url"]!!, values["body"]!!)
            comments.add(comment)
        }
        return comments
    }

    @TypeConverter
    fun commentToString(comment: Comment):String{
            return "{id=${comment.id}, name=${comment.name}, image_url=${comment.image_url}, body=${comment.body}}"
    }
    @TypeConverter
    fun stringToComment(string: String): Comment {
        val trimmedString = string.trim('{', '}')
        val keyValuePairs = trimmedString.split(',')
        val id = keyValuePairs[0].split('=')[1].trim()
        val name = keyValuePairs[1].split('=')[1].trim()
        val imageUrl = keyValuePairs[2].split('=')[1].trim()
        val body = keyValuePairs[3].split('=')[1].trim()
        return Comment(id, name, imageUrl, body)
    }
    @TypeConverter
    fun fromDaysToStrings(days: Days):String = days.toString()
    @TypeConverter
    fun fromStringsToDays(days:String):Days?{
        return when(days){
            Days.Friday.toString() -> Days.Friday
            Days.Monday.toString()->Days.Monday
            Days.Saturday.toString()->Days.Saturday
            Days.Sunday.toString()->Days.Sunday
            Days.Tuesday.toString()->Days.Tuesday
            Days.Wednesday.toString()->Days.Wednesday
            Days.Thursday.toString()->Days.Thursday
            else -> {return null}
        }
    }
    @TypeConverter
    fun stringToDaysList( string:String):List<Days> {
        val trimmedString = string.trim('[', ']')
        val elements = trimmedString.split(',')
        val list: List<String> = elements.map { it.trim() }
        val listDays: MutableList<Days> = mutableListOf()
        for (element in list) {
            if (element == "Saturday") {
                listDays.add(Days.Saturday)
            } else if (element == "Sunday") {
                listDays.add(Days.Sunday)
            } else if (element == "Monday") {
                listDays.add(Days.Monday)
            } else if (element == "Tuesday") {
                listDays.add(Days.Tuesday)
            } else if (element == "Wednesday") {
                listDays.add(Days.Wednesday)
            } else if (element == "Thursday") {
                listDays.add(Days.Thursday)
            } else {
                listDays.add(Days.Friday)
            }
        }
        return listDays
    }
    @TypeConverter
    fun daysListToString(daysList: List<Days>): String {
        val dayStrings = daysList.map {
            when (it) {
                Days.Saturday -> "Saturday"
                Days.Sunday -> "Sunday"
                Days.Monday -> "Monday"
                Days.Tuesday -> "Tuesday"
                Days.Wednesday -> "Wednesday"
                Days.Thursday -> "Thursday"
                Days.Friday -> "Friday"
            }
        }
        return dayStrings.joinToString(separator = ", ", prefix = "[", postfix = "]")
    }
    @TypeConverter
    fun stringToList(string: String): List<String> {
        try {
            val trimmedString = string.trim('[', ']')
            val elements = trimmedString.split(',')
            return elements.map { it.trim() }
        }catch (e:Exception){
            return listOf()
        }
    }
    @TypeConverter
    fun listToString(list: List<String>): String {
        return list.joinToString(",", "[", "]")
    }
    @TypeConverter
    fun stringToEmergencyContact(string: String): EmergencyContact {
        var emergencyContact = EmergencyContact("","")
        if (string != ""){
        val trimmedString = string.trim('{', '}')
        val keyValuePairs = trimmedString.split(',')
        val values = keyValuePairs.map { it.split('=')[1].trim() }
        emergencyContact = EmergencyContact(values[0],values[1])
        }
        return emergencyContact
    }
    @TypeConverter
    fun emergencyContactToString(emergencyContact: EmergencyContact?): String {
        val stringBuilder = StringBuilder()
        stringBuilder.append("{")
        stringBuilder.append("name=${emergencyContact?.name},")
        stringBuilder.append("phone=${emergencyContact?.phoneNumber}")
        stringBuilder.append("}")
        return stringBuilder.toString()
    }

    @TypeConverter
    fun stringToMedicalInsurance(string: String): MedicalInsurance {
        try {
        val trimmedString = string.trim('{', '}')
        val keyValuePairs = trimmedString.split(',')
        val values = keyValuePairs.map { it.split('=')[1].trim() }
        val medicalInsurance = MedicalInsurance(values[0],values[1]);
        return medicalInsurance
        }catch (e:Exception){return MedicalInsurance("","")}
    }
    @TypeConverter
    fun medicalInsuranceToString(medicalInsurance: MedicalInsurance?): String {
        val stringBuilder = StringBuilder()
        stringBuilder.append("{")
        stringBuilder.append("provider=${medicalInsurance?.insuranceProvider},")
        stringBuilder.append("policyNumber=${medicalInsurance?.id}")
        stringBuilder.append("}")
        return stringBuilder.toString()
    }

    @TypeConverter
    fun listStringsToListAppointments(string: List<String>):List<Appointment>{
        val appointments : MutableList<Appointment> = mutableListOf()
        for(str in string){
            val trimmedString = str.trim('{', '}')
            val keyValuePairs = trimmedString.split(',')
            val values = keyValuePairs.map { it.split('=')[1].trim() }
            Log.i(" remote",values.toString())
            val appointment = Appointment(values[0],values[6],values[3],values[4],values[2],values[1],values[5])
            appointments.add(appointment)
        }
        return appointments
    }
    @TypeConverter
    fun listAppointmentsToListStrings(appointments: List<Appointment>?): List<String> {
        val stringList: MutableList<String> = mutableListOf()
        if (appointments != null) {
            for (appointment in appointments) {
                val stringBuilder = StringBuilder()
                stringBuilder.append("{")
                stringBuilder.append("date=${appointment.date},")
                stringBuilder.append("doctorId=${appointment.dId},")
                stringBuilder.append("doctorName=${appointment.dName},")
                stringBuilder.append("id=${appointment.id},")
                stringBuilder.append("patientId=${appointment.pId},")
                stringBuilder.append("patientName=${appointment.pName},")
                stringBuilder.append("title=${appointment.title},")
                stringBuilder.append("}")
                stringList.add(stringBuilder.toString())
            }
        }
        return stringList
    }

    @TypeConverter
    fun listStringsToListDoctors(string: List<String>?):List<Doctor>{
        val doctors : MutableList<Doctor> = mutableListOf()
        if (string != null) {

            for(str in string){
                try {
                    val trimmedString = str.trim('{', '}',)
                    val keyValuePairs = trimmedString.split(',')
                    val values = keyValuePairs.map { it.split('=')[1].trim() }
                    val doctor = Doctor(
                        values[0],
                        values[1],
                        values[7],
                        "",
                        values[6],
                        values[3],
                        values[4],
                        values[5],
                        stringToDaysList(values[3])
                    )
                    doctors.add(doctor)
                }catch (ignore:Exception){}
            }
        }
        return doctors
    }
    @TypeConverter
    fun listDoctorsToListStrings(doctors: List<Doctor>?): List<String> {
        val stringList: MutableList<String> = mutableListOf()
        if (doctors != null) {
            for (doctor in doctors) {
                val stringBuilder = StringBuilder()
                stringBuilder.append("[")
                stringBuilder.append("address=${doctor.address},")
                stringBuilder.append("city=${doctor.city},")
                stringBuilder.append("email=${doctor.email},")
                stringBuilder.append("gender=${doctor.gender},")
                stringBuilder.append("id=${doctor.id},")
                stringBuilder.append("imageUrl=${doctor.imageURL},")
                stringBuilder.append("name=${doctor.name},")
                stringBuilder.append("telephone=${doctor.telephone},")
                stringBuilder.append("days=${daysListToString(doctor.workingDays!!)}")
                stringBuilder.append("]")
                stringList.add(stringBuilder.toString())
            }
        }
        return stringList
    }
    @TypeConverter
    fun fromStringToPatient(list: List<String>):Patient{
        Log.i(" testing type converter", list.toString())
        return Patient(
            list[0], list[1], list[2], list[3], list[4],
            stringToEmergencyContact(list[5]), list[6], list[7], list[8],
            stringToMedicalInsurance(list[9]),
            listOf(), list[10], list[11]
        )
    }


    fun fromStringToMiniPatient(list: List<String>):Patient{
        return Patient(name = list[0], id = list[1], email = list[2])
    }
    fun fromStringToMiniDoctor(list: List<String>):Doctor{
        return Doctor(name = list[0], id = list[1], email = list[2])
    }
    @TypeConverter
    fun fromPatientToString(patient: Patient): List<String?> {
        return listOf(patient.address, patient.birthDate, patient.bloodType, patient.city,
            patient.email,emergencyContactToString(patient.emergencyContact!!),patient.imageUrl,
            patient.id, medicalInsuranceToString(patient.insurance!!),"",
            patient.mobilePhone,patient.name)
    }
    @TypeConverter
    fun patientToPatientDto(patient: Patient):PatientsDTO {
        return PatientsDTO(patient.id!!,patient.name,patient.gender,patient.email,
            patient.birthDate,patient.imageUrl,patient.address,patient.city,patient.mobilePhone,
            patient.bloodType,patient.medicalIssues,patient.emergencyContact,patient.insurance)
    }
    @TypeConverter
    fun doctorToDoctorDTO(doctor: Doctor):DoctorsDTO {
        return DoctorsDTO(doctor.id!!,doctor.name,doctor.gender,doctor.workingDays!!,doctor.email,doctor.imageURL,doctor.city,doctor.telephone,doctor.address)
     }

    fun fromStringToDoctor(dataString:String): Doctor {
        // Split the string into key-value pairs
        val keyValuePairs = dataString.trim('{', '}').split(", ")
        Log.i(" tag", dataString)
        // Create a map to hold the values
        val values = mutableMapOf<String, String>()
        for (pair in keyValuePairs) {
            if (pair != ""){
            try {
                Log.i(" testing",pair.toString())
                val (key, value) = pair.split("=")
                values[key.trim()] = value.trim()
            }catch (e:Exception){
                values[pair.substring(0,pair.length-2)] = ""
            }
            }
        }

    // Create a Doctor object and assign the values from the map
        return Doctor(
            address = values["address"],
            city = values["city"],
            email = values["email"],
            gender = values["gender"],
            id = values["id"],
            imageURL = values["imageURL"],
            name = values["name"],
            telephone = values["telephone"]
        )
    }


}