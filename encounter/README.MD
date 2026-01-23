// PATIENT | DOCTOR | ENCOUNTER | APPOINTMENT
// PATIENT
// - id
// - name
// - dob

//DOCTOR
// -id
// -name
// -speciality


// ENCOUNTER
// - id
// - startTime - timestamp
// - endTime - timestamp
// - appointmentId - ref constraint
// - patientId - ref constraint
// - doctorId - ref constraint
//

// APPOINTMENT
// - id
// - patientId
// - appointemntDate
// - requestDate