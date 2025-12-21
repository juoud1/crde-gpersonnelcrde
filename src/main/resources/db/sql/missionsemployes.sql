CREATE TABLE IF NOT EXISTS mission_employe (employe_id bigint, 
mission_id bigint,
is_employe_chef_mission boolean,
miss_emp_creee_le TIMESTAMP(9) WITH TIME ZONE, miss_emp_creee_par VARCHAR(15), 
miss_emp_modifiee_le TIMESTAMP(9) WITH TIME ZONE, miss_emp_modifiee_par VARCHAR(15),
constraint pk_miss_emp_key primary key(employe_id, mission_id),
constraint fk_miss_emp_empkey FOREIGN key (employe_id) references employe(id),
constraint fk_miss_emp_misskey FOREIGN key (mission_id) references mission(id)
);
insert into mission_employe (employe_id, mission_id, is_employe_chef_mission, miss_emp_creee_le, miss_emp_creee_par, miss_emp_modifiee_le, miss_emp_modifiee_par) 
values (3, 1, true, '20250827', 'admin', '20250903', 'admin');