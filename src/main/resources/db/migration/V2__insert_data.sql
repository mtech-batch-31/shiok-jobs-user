INSERT INTO sjmsuser.user_profile (id, account_uuid, name,seeking,job_title,image,about)
VALUES
    (1, '06396421-0159-42cf-a6a6-64aac15cc4b1', 'Andrew Tan', true, 'Production Support Engineer', 'url', 'Reduced the number of customer support tickets by 15% by developing a new knowledge base.'),
    (2, '3af5923e-aeee-4c79-bb2d-4cbea3e03bd3', 'Ernest Lee', true, 'Senior Data Scientist', 'url','Led a team of engineers in the development of a new mobile app that has been downloaded over 1 million times.'),
    (3, null, 'Wang Shiji', false, 'Principal Engineer', 'url','Developed a new algorithm that improved the performance of our software by 20%.');

INSERT INTO sjmsuser.working_experience (id, profile_id, company, year_start, year_end,job_title,experience)
VALUES
    (1, 1, 'sptel','2012', '2015', 'Engineer','Performed engineering duties in multiple projects'),
    (2, 1, 'singtel','2013', '2015', 'Engineer','Performed engineering duties in multiple projects'),
    (3, 1, 'starhub','2014', '2015', 'Engineer','Performed engineering duties in multiple projects');

INSERT INTO sjmsuser.educational_experience (id, profile_id, school, year_start, year_end, description)
VALUES
    (1, 1, 'hwa chong institution', '2000','2008', 'integrated programme'),
    (2, 1, 'national university singapore', '2001','2009', 'bachelors'),
    (3, 1, 'singapore institute management', '2002','2010', 'masters of technology');