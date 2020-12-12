package com.padcx.shared.data.dummyData

import com.padcx.shared.data.vos.*

val dummyDoctor = DoctorVO(
    "D01", "Mg Mg", "mgmg@gmail.com",
    "09123456789", "password", "",
    "SP01", "2 years", "",
    "M.B.B.S", "University of Medicine (1), Yangon"
)

val dummyPatient = PatientVO(
    "P01", "Hla Hla", "hlahla@gmail.com",
    "password", "09123456789", ""
)

val dummyChatFromPatient = ChatVO(
    "CH01", "12/12/2020", "",
    "Hello Sir", "12 AM", dummyPatient
)

val dummyChatFromDoctor = ChatVO(
    "CH01", "12/12/2020", "",
    "Hello", "12:30 AM", dummyDoctor
)

val dummyRoutine = RoutineVO(
    5, "Lunch"
)

val dummyPrescription = PrescriptionVO(
    "PRES01", "M01", 4,
    "Paracetamol", 10, dummyRoutine
)

val dummyDeliveryRoutine = DeliveryRoutineVO(
    "12/12/2020", "4 PM"
)

val dummyPrescriptionList = arrayListOf(dummyPrescription, dummyPrescription)
val dummyChatList = arrayListOf(dummyChatFromDoctor, dummyChatFromPatient)

val dummyCheckOut = CheckOutVO(
    "CHOUT01", dummyDeliveryRoutine, dummyDoctor,
    dummyPatient, dummyPrescriptionList, "12/12/2020", "Kamayut", "SP01"
)

val dummyCaseSummary = CaseSummaryVO(
    "Panicellin", "", "B", "12/12/1996",
    "5 ft 4 inches", "140lb", "ခေါင်းကိုက်နေခြင်း"
)

val dummyConsultation = ConsultationVO(
    "CONS01", dummyCaseSummary, dummyDoctor, dummyPatient, dummyPrescriptionList,
    "SP01", dummyChatList
)
