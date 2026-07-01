INSERT INTO users
(phone, role, name, city, skills, age, experience, gender, created_at)
VALUES

-- Logistics / Delivery
('9000000001','RECRUITER','Delhivery Pvt Ltd','Delhi','Logistics & Delivery Company',NULL,NULL,'OTHER',NOW()),
('9000000002','RECRUITER','Shadowfax','Noida','Last Mile Delivery',NULL,NULL,'OTHER',NOW()),
('9000000003','RECRUITER','XpressBees','Gurgaon','E-commerce Logistics',NULL,NULL,'OTHER',NOW()),

-- Driver / Transport
('9000000004','RECRUITER','Uber Fleet Delhi','Delhi','Driver Hiring',NULL,NULL,'OTHER',NOW()),
('9000000005','RECRUITER','Ola Fleet Services','Noida','Cab Driver Hiring',NULL,NULL,'OTHER',NOW()),

-- Construction / Helpers
('9000000006','RECRUITER','Shapoorji Pallonji Group','Delhi','Construction Workers Hiring',NULL,NULL,'OTHER',NOW()),
('9000000007','RECRUITER','L&T Construction','Faridabad','Construction & Site Workers',NULL,NULL,'OTHER',NOW()),

-- Housekeeping / Facility
('9000000008','RECRUITER','Quess Corp','Gurgaon','Facility Management & Housekeeping',NULL,NULL,'OTHER',NOW()),
('9000000009','RECRUITER','Sodexo India','Delhi','Facility & Cleaning Services',NULL,NULL,'OTHER',NOW()),

-- Manufacturing / Garments
('9000000010','RECRUITER','Arvind Garments','Noida','Garment Manufacturing Company',NULL,NULL,'OTHER',NOW())

    ON CONFLICT (phone) DO NOTHING;