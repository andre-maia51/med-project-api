create table appointments (
   id bigserial not null,
   patientId bigserial not null,
   doctorId bigserial not null,

   primary key(id),
   constraint fk_appointment_doctorId foreign key(doctorId) references doctors(id),
   constraint fk_appointment_patientId foreign key(patientId) references patients(id)
);