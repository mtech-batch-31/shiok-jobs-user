INSERT INTO sjmsuser.user_profile (id, account_uuid, name, seeking, job_title, image, about)
VALUES
    (1, '06396421-0159-42cf-a6a6-64aac15cc4b1', 'Andrew Tan', true, 'Production Support Engineer', 'url', 'Senior Analyst with a demonstrated history of working in the information technology and services industry. Skilled in Quality Assurance, System Integration Testing, System Testing, Testing, SQL. Strong IT professional with a Bachelor''s degree focused in Electrical and Electronic Engineering from National University of Singapore.'),
    (2, '3af5923e-aeee-4c79-bb2d-4cbea3e03bd3', 'Ernest Lee', true, 'Senior Data Scientist', 'url', 'Experienced Data Analyst in the information technology and services sector, showcasing a track record of success. Proficient in areas such as Machine Learning, Data Analytics, Big Data, Artificial Intelligence. A dedicated domain expert with a Bachelor''s degree specializing in Computer Science from Nanyang Technological University.'),
    (3, null, 'Wang Shiji', false, 'Principal Engineer', 'url', 'Principal Engineer in the information technology and services industry, demonstrating a history of achievements. Proficient in cutting-edge domains like Big Data and AI. A dedicated domain expert holding a Bachelor''s degree with a focus on Business Analytics from Singapore Institute of Management.');

INSERT INTO sjmsuser.working_experience (id, profile_id, company, year_start, year_end,job_title,experience)
VALUES
    (1, 1, 'SPTel Pte Ltd','June 2022', 'Present', 'Senior Analyst, OSS','Level 1 and Level 2 support for OSS, BSS and Billing production incidents. Responsibilities include "Keep the lights on" for platform & application services. IT Service Management (ITSM) under Product and Business IT (BizIT) team'),
    (2, 1, 'PCCW Solutions','Sep 2019', 'May 2022', 'System Analyst, Test','Provided Test Services ST, SIT, UAT, ORT to greenfield telco (Project Symphony). Created, reviewed, executed test cases during SIT and UAT phase of the project. Liaised closely with business, development, solutioning and project management for systems including OSS, CRM and Billing for Assurance and Fulfillment'),
    (3, 1, 'StarHub','May 2017', 'Sep 2019', 'Test Analyst','Test Designer, SIT Project SPOC, UAT Project SPOC for major telco. System Integration Testing systems include Unix, Weblogic, Java, Windows, iOS, Android. User Acceptance Testing domains include Product, Finance Billing, CRM, Retail Applications');

INSERT INTO sjmsuser.educational_experience (id, profile_id, school, year_start, year_end, description)
VALUES
    (1, 1, 'Hwa Chong Institution', '2000','2005', 'A Levels, Physics, Chemistry, Mathematics, Biology'),
    (2, 1, 'Nanyang Technological University Singapore', '2008','2012', 'B.Eng. in Electrical and Electronic Engineering');