
INSERT INTO PUBLIC.ACCESS_CODE(ID, ACCESS_CODE, ACCESS_DESC, MAPPING, METHOD) VALUES
(1, 'READ_ACCESS_CODE', 'read access code', '/resource/accessCode', 'GET');        
  
INSERT INTO PUBLIC.APP_CONFIGURATION(ID, CREATED_BY, CREATED_DATE, CONFIG_KEY, MODIFIED_BY, MODIFIED_DATE, VALUE) VALUES
(1, 'vkarthik', TIMESTAMP '2017-07-09 17:50:25.345', 'WIKI_DOMAIN', 'vkarthik', TIMESTAMP '2017-07-09 17:50:25.345', 'http://localhost:8080/wiki/view/'); 
          
            
INSERT INTO PUBLIC.DEPARTMENT(ID, CREATED_BY, CREATED_DATE, DEPARTMENT_NAME, MANAGER_ID, MODIFIED_BY, MODIFIED_DATE, PARENT_DEPARTMENT_ID) VALUES
(1, 'vkarthik', TIMESTAMP '2017-07-09 18:10:04.211', 'TTO', 'bbroko', 'vkarthik', TIMESTAMP '2017-07-09 18:10:04.211', NULL),
(2, 'vkarthik', TIMESTAMP '2017-07-09 18:10:18.146', 'TSO', 'rajini', 'vkarthik', TIMESTAMP '2017-07-09 18:10:18.146', 1),
(3, 'vkarthik', TIMESTAMP '2017-07-09 18:10:26.399', 'TXO', 'kamal', 'vkarthik', TIMESTAMP '2017-07-09 18:10:26.399', 1);           
        
INSERT INTO PUBLIC.ESTIMATES(ID, CREATED_BY, CREATED_DATE, ESTIMATE, ESTIMATION_UNIT, MODIFIED_BY, MODIFIED_DATE, PHASE, PROJECT_ID, TEAM_ID) VALUES
(1, 'vkarthik', TIMESTAMP '2017-07-09 21:47:00.144', 10, 'MDS', 'vkarthik', TIMESTAMP '2017-07-09 21:47:00.144', 'Dev', 1, 1),
(2, 'vkarthik', TIMESTAMP '2017-07-09 21:47:00.148', 10, 'MDS', 'vkarthik', TIMESTAMP '2017-07-09 21:47:00.148', 'analysis', 1, 1),
(3, 'vkarthik', TIMESTAMP '2017-07-09 21:47:00.148', 10, 'MDS', 'vkarthik', TIMESTAMP '2017-07-09 21:47:00.148', 'Design', 1, 1),
(4, 'vkarthik', TIMESTAMP '2017-07-09 21:47:00.148', 10, 'MDS', 'vkarthik', TIMESTAMP '2017-07-09 21:47:00.148', 'dev-int', 1, 1),
(5, 'vkarthik', TIMESTAMP '2017-07-09 21:47:00.148', 10, 'MDS', 'vkarthik', TIMESTAMP '2017-07-09 21:47:00.148', 'prod-support', 1, 1),
(6, 'vkarthik', TIMESTAMP '2017-07-09 21:47:00.152', 10, 'MDS', 'vkarthik', TIMESTAMP '2017-07-09 21:47:00.152', 'UAT-support', 1, 1),
(7, 'vkarthik', TIMESTAMP '2017-07-09 21:47:00.152', 10, 'MDS', 'vkarthik', TIMESTAMP '2017-07-09 21:47:00.152', 'SIT-support', 1, 1),
(8, 'vkarthik', TIMESTAMP '2017-07-09 21:50:02.414', 10, 'MDS', 'vkarthik', TIMESTAMP '2017-07-09 21:50:02.414', 'Dev', 1, 2),
(9, 'vkarthik', TIMESTAMP '2017-07-09 21:50:02.419', 10, 'MDS', 'vkarthik', TIMESTAMP '2017-07-09 21:50:02.419', 'analysis', 1, 2),
(10, 'vkarthik', TIMESTAMP '2017-07-09 21:50:02.419', 10, 'MDS', 'vkarthik', TIMESTAMP '2017-07-09 21:50:02.419', 'Design', 1, 2),
(11, 'vkarthik', TIMESTAMP '2017-07-09 21:50:02.419', 10, 'MDS', 'vkarthik', TIMESTAMP '2017-07-09 21:50:02.419', 'dev-int', 1, 3),
(12, 'vkarthik', TIMESTAMP '2017-07-09 21:50:02.419', 10, 'MDS', 'vkarthik', TIMESTAMP '2017-07-09 21:50:02.419', 'prod-support', 1, 3),
(13, 'vkarthik', TIMESTAMP '2017-07-09 21:50:02.419', 10, 'MDS', 'vkarthik', TIMESTAMP '2017-07-09 21:50:02.419', 'UAT-support', 1, 3),
(15, NULL, TIMESTAMP '2017-07-11 22:41:49.169', 5, 'Man-Days', NULL, TIMESTAMP '2017-07-11 22:41:49.169', 'Dev', 1, 4);           
   
INSERT INTO PUBLIC.PAGE(ID, CONTENT, CREATED_BY, CREATED_DATE, MODIFIED_BY, MODIFIED_DATE, TITLE, URL_FRIENDLY_TITLE) VALUES
(1, '### My Project number 1', 'vkarthik', TIMESTAMP '2017-07-09 18:35:11.47', 'vkarthik', TIMESTAMP '2017-07-09 18:35:11.47', 'My Project number 1', 'my_project_number_1'),
(2, STRINGDECODE('[My First Project](/wiki/view/My_Project_number_1)\r\n'), 'vkarthik', TIMESTAMP '2017-07-09 18:42:26.093', 'vkarthik', TIMESTAMP '2017-07-09 18:42:26.093', 'sidebar', 'sidebar');             

INSERT INTO PUBLIC.PROJECT(ID, CREATED_BY, CREATED_DATE, DESCRIPTION, MANAGERS, MODIFIED_BY, MODIFIED_DATE, NAME, OWNERS, PROGRAM_ID, STATUS, SUMMARY, TARGET_DATE, TYPE) VALUES
(1, 'vkarthik', TIMESTAMP '2017-07-09 18:32:56.169', NULL, NULL, 'vkarthik', TIMESTAMP '2017-07-09 18:32:56.169', 'My Project number 1', NULL, NULL, 'New', NULL, TIMESTAMP '2017-07-09 18:32:56.169', NULL),
(2, 'vkarthik', TIMESTAMP '2017-07-16 12:14:14.035', NULL, NULL, 'vkarthik', TIMESTAMP '2017-07-16 12:14:14.035', 'My Project number 2', NULL, NULL, 'New', NULL, TIMESTAMP '2017-07-16 12:14:14.035', NULL),
(3, 'vkarthik', TIMESTAMP '2017-07-16 12:14:22.397', NULL, NULL, 'vkarthik', TIMESTAMP '2017-07-16 12:14:22.397', 'My Project number 3', NULL, NULL, 'New', NULL, TIMESTAMP '2017-07-16 12:14:22.397', NULL),
(4, 'vkarthik', TIMESTAMP '2017-07-16 12:14:29.552', NULL, NULL, 'vkarthik', TIMESTAMP '2017-07-16 12:14:29.552', 'My Project number 4', NULL, NULL, 'New', NULL, TIMESTAMP '2017-07-16 12:14:29.552', NULL),
(5, 'vkarthik', TIMESTAMP '2017-07-16 12:14:36.435', NULL, NULL, 'vkarthik', TIMESTAMP '2017-07-16 12:14:36.435', 'My Project number 5', NULL, NULL, 'New', NULL, TIMESTAMP '2017-07-16 12:14:36.435', NULL),
(6, 'vkarthik', TIMESTAMP '2017-07-16 12:14:43.312', NULL, NULL, 'vkarthik', TIMESTAMP '2017-07-16 12:14:43.312', 'My Project number 6', NULL, NULL, 'New', NULL, TIMESTAMP '2017-07-16 12:14:43.312', NULL),
(7, 'vkarthik', TIMESTAMP '2017-07-16 12:14:50.523', NULL, NULL, 'vkarthik', TIMESTAMP '2017-07-16 12:14:50.523', 'My Project number 7', NULL, NULL, 'New', NULL, TIMESTAMP '2017-07-16 12:14:50.523', NULL),
(8, 'vkarthik', TIMESTAMP '2017-07-16 12:14:58.579', NULL, NULL, 'vkarthik', TIMESTAMP '2017-07-16 12:14:58.579', 'My Project number 8', NULL, NULL, 'New', NULL, TIMESTAMP '2017-07-16 12:14:58.579', NULL),
(9, 'vkarthik', TIMESTAMP '2017-07-16 12:15:07.459', NULL, NULL, 'vkarthik', TIMESTAMP '2017-07-16 12:15:07.459', 'My Project number 9', NULL, NULL, 'New', NULL, TIMESTAMP '2017-07-16 12:15:07.459', NULL),
(10, 'vkarthik', TIMESTAMP '2017-07-16 12:15:15.125', NULL, NULL, 'vkarthik', TIMESTAMP '2017-07-16 12:15:15.125', 'My Project number 10', NULL, NULL, 'New', NULL, TIMESTAMP '2017-07-16 12:15:15.125', NULL),
(11, 'vkarthik', TIMESTAMP '2017-07-16 12:15:23.085', NULL, NULL, 'vkarthik', TIMESTAMP '2017-07-16 12:15:23.085', 'My Project number 11', NULL, NULL, 'New', NULL, TIMESTAMP '2017-07-16 12:15:23.085', NULL),
(12, 'vkarthik', TIMESTAMP '2017-07-16 12:15:32.222', NULL, NULL, 'vkarthik', TIMESTAMP '2017-07-16 12:15:32.222', 'My Project number 12', NULL, NULL, 'New', NULL, TIMESTAMP '2017-07-16 12:15:32.222', NULL),
(13, 'vkarthik', TIMESTAMP '2017-07-16 12:15:38.648', NULL, NULL, 'vkarthik', TIMESTAMP '2017-07-16 12:15:38.648', 'My Project number 13', NULL, NULL, 'New', NULL, TIMESTAMP '2017-07-16 12:15:38.648', NULL),
(14, 'vkarthik', TIMESTAMP '2017-07-16 12:15:47.012', NULL, NULL, 'vkarthik', TIMESTAMP '2017-07-16 12:15:47.012', 'My Project number 14', NULL, NULL, 'New', NULL, TIMESTAMP '2017-07-16 12:15:47.012', NULL),
(15, 'vkarthik', TIMESTAMP '2017-07-16 12:15:55.128', NULL, NULL, 'vkarthik', TIMESTAMP '2017-07-16 12:15:55.128', 'My Project number 15', NULL, NULL, 'New', NULL, TIMESTAMP '2017-07-16 12:15:55.128', NULL),
(16, 'vkarthik', TIMESTAMP '2017-07-16 12:16:14.934', NULL, NULL, 'vkarthik', TIMESTAMP '2017-07-16 12:16:14.934', 'My Project number 16', NULL, NULL, 'New', NULL, TIMESTAMP '2017-07-16 12:16:14.934', NULL),
(17, 'vkarthik', TIMESTAMP '2017-07-16 12:16:21.645', NULL, NULL, 'vkarthik', TIMESTAMP '2017-07-16 12:16:21.645', 'My Project number 17', NULL, NULL, 'New', NULL, TIMESTAMP '2017-07-16 12:16:21.645', NULL),
(18, 'vkarthik', TIMESTAMP '2017-07-16 12:16:31.08', NULL, NULL, 'vkarthik', TIMESTAMP '2017-07-16 12:16:31.08', 'My Project number 18', NULL, NULL, 'New', NULL, TIMESTAMP '2017-07-16 12:16:31.08', NULL),
(19, 'vkarthik', TIMESTAMP '2017-07-16 12:16:39.147', NULL, NULL, 'vkarthik', TIMESTAMP '2017-07-16 12:16:39.147', 'My Project number 19', NULL, NULL, 'New', NULL, TIMESTAMP '2017-07-16 12:16:39.147', NULL);
INSERT INTO PUBLIC.PROJECT(ID, CREATED_BY, CREATED_DATE, DESCRIPTION, MANAGERS, MODIFIED_BY, MODIFIED_DATE, NAME, OWNERS, PROGRAM_ID, STATUS, SUMMARY, TARGET_DATE, TYPE) VALUES
(20, 'vkarthik', TIMESTAMP '2017-07-16 12:16:46.845', NULL, NULL, 'vkarthik', TIMESTAMP '2017-07-16 12:16:46.845', 'My Project number 20', NULL, NULL, 'New', NULL, TIMESTAMP '2017-07-16 12:16:46.845', NULL),
(21, 'vkarthik', TIMESTAMP '2017-07-16 12:16:55.183', NULL, NULL, 'vkarthik', TIMESTAMP '2017-07-16 12:16:55.183', 'My Project number 21', NULL, NULL, 'New', NULL, TIMESTAMP '2017-07-16 12:16:55.183', NULL),
(22, 'vkarthik', TIMESTAMP '2017-07-16 12:17:02.422', NULL, NULL, 'vkarthik', TIMESTAMP '2017-07-16 12:17:02.422', 'My Project number 22', NULL, NULL, 'New', NULL, TIMESTAMP '2017-07-16 12:17:02.422', NULL),
(23, 'vkarthik', TIMESTAMP '2017-07-16 12:17:11.428', NULL, NULL, 'vkarthik', TIMESTAMP '2017-07-16 12:17:11.428', 'My Project number 23', NULL, NULL, 'New', NULL, TIMESTAMP '2017-07-16 12:17:11.428', NULL),
(25, 'vkarthik', TIMESTAMP '2017-07-16 12:17:27.228', NULL, NULL, 'vkarthik', TIMESTAMP '2017-07-16 12:17:27.228', 'My Project number 24', NULL, NULL, 'New', NULL, TIMESTAMP '2017-07-16 12:17:27.228', NULL),
(26, 'vkarthik', TIMESTAMP '2017-07-16 12:17:38.229', NULL, NULL, 'vkarthik', TIMESTAMP '2017-07-16 12:17:38.229', 'My Project number 25', NULL, NULL, 'New', NULL, TIMESTAMP '2017-07-16 12:17:38.229', NULL),
(27, 'vkarthik', TIMESTAMP '2017-07-16 12:17:46.007', NULL, NULL, 'vkarthik', TIMESTAMP '2017-07-16 12:17:46.007', 'My Project number 26', NULL, NULL, 'New', NULL, TIMESTAMP '2017-07-16 12:17:46.007', NULL),
(28, 'vkarthik', TIMESTAMP '2017-07-16 12:17:53.296', NULL, NULL, 'vkarthik', TIMESTAMP '2017-07-16 12:17:53.296', 'My Project number 27', NULL, NULL, 'New', NULL, TIMESTAMP '2017-07-16 12:17:53.296', NULL),
(29, 'vkarthik', TIMESTAMP '2017-07-16 12:18:01.16', NULL, NULL, 'vkarthik', TIMESTAMP '2017-07-16 12:18:01.16', 'My Project number 28', NULL, NULL, 'New', NULL, TIMESTAMP '2017-07-16 12:18:01.16', NULL),
(30, 'vkarthik', TIMESTAMP '2017-07-16 12:18:09.211', NULL, NULL, 'vkarthik', TIMESTAMP '2017-07-16 12:18:09.211', 'My Project number 29', NULL, NULL, 'New', NULL, TIMESTAMP '2017-07-16 12:18:09.211', NULL),
(31, 'vkarthik', TIMESTAMP '2017-07-16 12:18:16.421', NULL, NULL, 'vkarthik', TIMESTAMP '2017-07-16 12:18:16.421', 'My Project number 30', NULL, NULL, 'New', NULL, TIMESTAMP '2017-07-16 12:18:16.421', NULL),
(32, 'vkarthik', TIMESTAMP '2017-07-16 12:18:27.617', NULL, NULL, 'vkarthik', TIMESTAMP '2017-07-16 12:18:27.617', 'My Project number 31', NULL, NULL, 'New', NULL, TIMESTAMP '2017-07-16 12:18:27.617', NULL);     
      
INSERT INTO PUBLIC.PROJECT_STATUS(STATUS) VALUES
('New'),
('Live'),
('Cancelled'),
('Rejected'),
('Completed'),
('Planned'),
('Elaboration'),
('Design'),
('Ready for Development'),
('In Development');            
         
INSERT INTO PUBLIC.RESOURCE_PLAN(ID, COMMENTS, CREATED_BY, CREATED_DATE, EFFORT_PER_DAY, EFFORT_PERCENT, END_DATE, MODIFIED_BY, MODIFIED_DATE, PHASE, PROJECT_ID, RESOURCE_ID, START_DATE, TEAM_ID, TOTAL_EFFORT) VALUES
(2, NULL, NULL, TIMESTAMP '2017-07-16 20:02:08.211', 0.0, 80, DATE '2017-07-31', NULL, TIMESTAMP '2017-07-16 20:02:08.212', 'dev', 7, 'teamMember2', DATE '2017-07-18', 1, 0.0),
(3, NULL, NULL, TIMESTAMP '2017-07-16 20:02:11.955', 0.0, 80, DATE '2017-07-31', NULL, TIMESTAMP '2017-07-16 20:02:11.955', 'dev', 7, 'teamMember3', DATE '2017-07-18', 1, 0.0),
(4, NULL, NULL, TIMESTAMP '2017-07-16 20:02:21.228', 0.0, 80, DATE '2017-07-31', NULL, TIMESTAMP '2017-07-16 20:02:21.229', 'dev', 7, 'teamMember3', DATE '2017-07-09', 1, 0.0),
(5, NULL, NULL, TIMESTAMP '2017-07-16 20:02:32.275', 0.0, 80, DATE '2017-07-31', NULL, TIMESTAMP '2017-07-16 20:02:32.275', 'dev', 9, 'teamMember3', DATE '2017-07-09', 1, 0.0),
(6, NULL, NULL, TIMESTAMP '2017-07-16 20:11:11.798', 5.6, 70, DATE '2017-07-31', NULL, TIMESTAMP '2017-07-16 20:11:11.802', 'dev', 1, 'teamMember6', DATE '2017-07-17', 2, 78.39999999999999),
(7, NULL, NULL, TIMESTAMP '2017-07-16 20:20:03.432', 1.6, 20, DATE '2017-08-10', NULL, TIMESTAMP '2017-07-16 20:20:03.437', 'test', 4, 'teamMember8', DATE '2017-07-02', 2, 62.4),
(8, NULL, NULL, TIMESTAMP '2017-07-16 20:20:46.101', 6.4, 80, DATE '2017-07-31', NULL, TIMESTAMP '2017-07-16 20:20:46.102', 'test', 4, 'teamMember8', DATE '2017-07-17', 2, 89.6),
(9, NULL, NULL, TIMESTAMP '2017-07-16 20:21:02.245', 5.6, 70, DATE '2017-07-31', NULL, TIMESTAMP '2017-07-16 20:21:02.245', 'test', 4, 'teamMember8', DATE '2017-07-17', 2, 78.4),
(10, 'COmmets added', NULL, TIMESTAMP '2017-07-16 20:54:40.0', 5.6, 70, DATE '2017-07-31', NULL, TIMESTAMP '2017-07-16 20:55:09.926', 'test', 32, 'teamMember9', DATE '2017-07-17', 2, 78.4),
(12, 'changed from proj 6 to 2', NULL, TIMESTAMP '2017-07-16 20:50:41.0', 5.6, 70, DATE '2017-07-31', NULL, TIMESTAMP '2017-07-16 20:54:40.057', 'test', 2, 'teamMember6', DATE '2017-07-17', 2, 78.4);          

INSERT INTO PUBLIC.ROLE(ID, CREATED_BY, CREATED_DATE, MODIFIED_BY, MODIFIED_DATE, ROLE_CODE, ROLE_DESCRIPTION, STATUS) VALUES
(1, 'vkarthik', TIMESTAMP '2017-07-09 17:46:07.911', 'vkarthik', TIMESTAMP '2017-07-09 17:46:07.911', 'ADMIN', 'administrator', 'Active'),
(2, 'vkarthik', TIMESTAMP '2017-07-09 17:46:42.899', 'vkarthik', TIMESTAMP '2017-07-09 17:46:42.899', 'DEVELOPER', 'Developer', 'Active'),
(3, 'vkarthik', TIMESTAMP '2017-07-09 17:47:04.57', 'vkarthik', TIMESTAMP '2017-07-09 17:47:04.57', 'TEAM_LEAD', 'team leader', 'Active'),
(4, 'vkarthik', TIMESTAMP '2017-07-09 17:47:17.207', 'vkarthik', TIMESTAMP '2017-07-09 17:47:17.207', 'MODULE_LEAD', 'module leader', 'Active'),
(5, 'vkarthik', TIMESTAMP '2017-07-09 17:47:30.952', 'vkarthik', TIMESTAMP '2017-07-09 17:47:30.952', 'TECH_LEAD', 'technical leader', 'Active'),
(6, 'vkarthik', TIMESTAMP '2017-07-09 17:47:50.86', 'vkarthik', TIMESTAMP '2017-07-09 17:47:50.86', 'PROJECT_MANAGER', 'project manager', 'Active'),
(7, 'vkarthik', TIMESTAMP '2017-07-09 17:48:49.346', 'vkarthik', TIMESTAMP '2017-07-09 17:48:49.346', 'ARCHITECT', 'Architect', 'Active'),
(8, 'vkarthik', TIMESTAMP '2017-07-09 17:49:03.528', 'vkarthik', TIMESTAMP '2017-07-09 17:49:03.528', 'DELIVERY_MANAGER', 'delivery manager', 'Active');            
      
INSERT INTO PUBLIC.ROLE_ACCESS_XREF(ID, ACCESS_CODE, CREATED_BY, CREATED_DATE, MODIFIED_BY, MODIFIED_DATE, ROLE_CODE) VALUES
(1, 'READ_ACCESS_CODE', 'vkarthik', TIMESTAMP '2017-07-09 18:07:50.699', 'vkarthik', TIMESTAMP '2017-07-09 18:07:50.699', 'ADMIN'),
(2, 'READ_ACCESS_CODE', 'vkarthik', TIMESTAMP '2017-07-09 18:07:54.729', 'vkarthik', TIMESTAMP '2017-07-09 18:07:54.729', 'DEVELOPER');    

INSERT INTO PUBLIC.TEAM(ID, CREATED_BY, CREATED_DATE, DEPARTMENT_ID, MANAGER_ID, MODIFIED_BY, MODIFIED_DATE, TEAM_NAME) VALUES
(1, 'vkarthik', TIMESTAMP '2017-07-09 18:11:27.792', NULL, 'vkarthik', 'vkarthik', TIMESTAMP '2017-07-09 18:11:27.792', 'OrderManagement'),
(2, 'vkarthik', TIMESTAMP '2017-07-09 18:11:42.591', NULL, 'bbroko', 'vkarthik', TIMESTAMP '2017-07-09 18:11:42.591', 'Shipment'),
(3, 'vkarthik', TIMESTAMP '2017-07-09 18:11:55.96', NULL, 'satiyaraj', 'vkarthik', TIMESTAMP '2017-07-09 18:11:55.96', 'Defect Tracking'),
(4, 'vkarthik', TIMESTAMP '2017-07-09 18:12:08.88', NULL, 'vijay', 'vkarthik', TIMESTAMP '2017-07-09 18:12:08.88', 'Online'),
(5, 'vkarthik', TIMESTAMP '2017-07-09 18:12:21.272', NULL, 'ajith', 'vkarthik', TIMESTAMP '2017-07-09 18:12:21.272', 'Payment'),
(6, 'vkarthik', TIMESTAMP '2017-07-09 18:12:35.847', NULL, 'rajini', 'vkarthik', TIMESTAMP '2017-07-09 18:12:35.847', 'Accountant');            
         
INSERT INTO PUBLIC.TEAM_MEMBER(ID, ALLOCATION_PERCENT, CREATED_BY, CREATED_DATE, MODIFIED_BY, MODIFIED_DATE, TEAM_ID, USER_ID) VALUES
(1, NULL, 'vkarthik', TIMESTAMP '2017-07-09 18:28:54.529', 'vkarthik', TIMESTAMP '2017-07-09 18:28:54.529', 1, 'teamMember1'),
(2, NULL, 'vkarthik', TIMESTAMP '2017-07-09 18:28:58.427', 'vkarthik', TIMESTAMP '2017-07-09 18:28:58.427', 1, 'teamMember2'),
(3, NULL, 'vkarthik', TIMESTAMP '2017-07-09 18:29:01.442', 'vkarthik', TIMESTAMP '2017-07-09 18:29:01.442', 1, 'teamMember3'),
(4, NULL, 'vkarthik', TIMESTAMP '2017-07-09 18:29:04.53', 'vkarthik', TIMESTAMP '2017-07-09 18:29:04.53', 1, 'teamMember4'),
(5, NULL, 'vkarthik', TIMESTAMP '2017-07-09 18:29:07.684', 'vkarthik', TIMESTAMP '2017-07-09 18:29:07.684', 1, 'teamMember5'),
(6, NULL, 'vkarthik', TIMESTAMP '2017-07-09 18:29:16.067', 'vkarthik', TIMESTAMP '2017-07-09 18:29:16.067', 2, 'teamMember6'),
(7, NULL, 'vkarthik', TIMESTAMP '2017-07-09 18:29:21.242', 'vkarthik', TIMESTAMP '2017-07-09 18:29:21.242', 2, 'teamMember7'),
(8, NULL, 'vkarthik', TIMESTAMP '2017-07-09 18:29:24.035', 'vkarthik', TIMESTAMP '2017-07-09 18:29:24.035', 2, 'teamMember8'),
(9, NULL, 'vkarthik', TIMESTAMP '2017-07-09 18:29:26.764', 'vkarthik', TIMESTAMP '2017-07-09 18:29:26.764', 2, 'teamMember9'),
(10, NULL, 'vkarthik', TIMESTAMP '2017-07-09 18:29:29.786', 'vkarthik', TIMESTAMP '2017-07-09 18:29:29.786', 2, 'teamMember10'),
(11, NULL, 'vkarthik', TIMESTAMP '2017-07-09 18:29:36.811', 'vkarthik', TIMESTAMP '2017-07-09 18:29:36.811', 3, 'teamMember11'),
(12, NULL, 'vkarthik', TIMESTAMP '2017-07-09 18:29:39.785', 'vkarthik', TIMESTAMP '2017-07-09 18:29:39.785', 3, 'teamMember12'),
(13, NULL, 'vkarthik', TIMESTAMP '2017-07-09 18:29:42.594', 'vkarthik', TIMESTAMP '2017-07-09 18:29:42.594', 3, 'teamMember13'),
(14, NULL, 'vkarthik', TIMESTAMP '2017-07-09 18:29:46.835', 'vkarthik', TIMESTAMP '2017-07-09 18:29:46.835', 3, 'teamMember14'),
(15, NULL, 'vkarthik', TIMESTAMP '2017-07-09 18:29:49.995', 'vkarthik', TIMESTAMP '2017-07-09 18:29:49.995', 3, 'teamMember15'),
(16, NULL, 'vkarthik', TIMESTAMP '2017-07-09 18:29:56.156', 'vkarthik', TIMESTAMP '2017-07-09 18:29:56.156', 4, 'teamMember16'),
(17, NULL, 'vkarthik', TIMESTAMP '2017-07-09 18:30:00.893', 'vkarthik', TIMESTAMP '2017-07-09 18:30:00.893', 4, 'teamMember17'),
(18, NULL, 'vkarthik', TIMESTAMP '2017-07-09 18:30:03.46', 'vkarthik', TIMESTAMP '2017-07-09 18:30:03.46', 4, 'teamMember18'),
(19, NULL, 'vkarthik', TIMESTAMP '2017-07-09 18:30:06.348', 'vkarthik', TIMESTAMP '2017-07-09 18:30:06.348', 4, 'teamMember19'),
(20, NULL, 'vkarthik', TIMESTAMP '2017-07-09 18:30:09.121', 'vkarthik', TIMESTAMP '2017-07-09 18:30:09.121', 4, 'teamMember20'),
(21, NULL, 'vkarthik', TIMESTAMP '2017-07-09 18:30:15.025', 'vkarthik', TIMESTAMP '2017-07-09 18:30:15.025', 5, 'teamMember21'),
(22, NULL, 'vkarthik', TIMESTAMP '2017-07-09 18:30:17.386', 'vkarthik', TIMESTAMP '2017-07-09 18:30:17.386', 5, 'teamMember22'),
(23, NULL, 'vkarthik', TIMESTAMP '2017-07-09 18:30:19.857', 'vkarthik', TIMESTAMP '2017-07-09 18:30:19.857', 5, 'teamMember23'),
(24, NULL, 'vkarthik', TIMESTAMP '2017-07-09 18:30:22.722', 'vkarthik', TIMESTAMP '2017-07-09 18:30:22.722', 5, 'teamMember24'),
(25, NULL, 'vkarthik', TIMESTAMP '2017-07-09 18:30:26.05', 'vkarthik', TIMESTAMP '2017-07-09 18:30:26.05', 5, 'teamMember25'),
(26, NULL, 'vkarthik', TIMESTAMP '2017-07-09 18:30:30.482', 'vkarthik', TIMESTAMP '2017-07-09 18:30:30.482', 6, 'teamMember26'),
(27, NULL, 'vkarthik', TIMESTAMP '2017-07-09 18:30:33.433', 'vkarthik', TIMESTAMP '2017-07-09 18:30:33.433', 6, 'teamMember27'),
(28, NULL, 'vkarthik', TIMESTAMP '2017-07-09 18:30:37.522', 'vkarthik', TIMESTAMP '2017-07-09 18:30:37.522', 6, 'teamMember28'),
(29, NULL, 'vkarthik', TIMESTAMP '2017-07-09 18:30:40.722', 'vkarthik', TIMESTAMP '2017-07-09 18:30:40.722', 6, 'teamMember29'),
(30, NULL, 'vkarthik', TIMESTAMP '2017-07-09 18:30:43.65', 'vkarthik', TIMESTAMP '2017-07-09 18:30:43.65', 6, 'teamMember30'),
(32, NULL, 'vkarthik', TIMESTAMP '2017-07-09 18:31:35.395', 'vkarthik', TIMESTAMP '2017-07-09 18:31:35.395', 2, 'vkarthik');         
INSERT INTO PUBLIC.TEAM_MEMBER(ID, ALLOCATION_PERCENT, CREATED_BY, CREATED_DATE, MODIFIED_BY, MODIFIED_DATE, TEAM_ID, USER_ID) VALUES
(33, NULL, 'vkarthik', TIMESTAMP '2017-07-09 18:31:39.896', 'vkarthik', TIMESTAMP '2017-07-09 18:31:39.896', 3, 'vkarthik'),
(34, NULL, 'vkarthik', TIMESTAMP '2017-07-09 18:31:44.401', 'vkarthik', TIMESTAMP '2017-07-09 18:31:44.401', 4, 'vkarthik'),
(35, NULL, 'vkarthik', TIMESTAMP '2017-07-09 18:31:50.224', 'vkarthik', TIMESTAMP '2017-07-09 18:31:50.224', 5, 'vkarthik'),
(36, NULL, 'vkarthik', TIMESTAMP '2017-07-09 18:31:56.019', 'vkarthik', TIMESTAMP '2017-07-09 18:31:56.019', 6, 'vkarthik'); 

INSERT INTO PUBLIC.USER(USER_ID, CREATED_BY, CREATED_DATE, EMAIL, FIRST_NAME, MODIFIED_BY, MODIFIED_DATE, PASSWORD, SECOND_NAME, STATE) VALUES
('vkarthik', NULL, TIMESTAMP '2017-07-09 15:13:37.475', 'nvijaykarthik@gmail.com', 'vijaykarthik', NULL, TIMESTAMP '2017-07-09 15:13:37.475', '$2a$10$LkBg/6EGbyn3DLkt5Jv/oe2K084T8vaomXBXdO9CnuKqNCsy3oaXu', 'Nagarajan', 'Active'),
('bbroko', NULL, TIMESTAMP '2017-07-09 17:42:11.843', 'bbroko@gmail.com', 'broko', NULL, TIMESTAMP '2017-07-09 17:42:11.843', '$2a$10$AnZPnG81C4iR3pTltM.UJ.SoxLCif5NGhSRsQm3iCvB61cLJi6WVa', 'b', 'Active'),
('rajini', NULL, TIMESTAMP '2017-07-09 17:43:18.479', 'rajini.kanth@hero.com', 'rajini', NULL, TIMESTAMP '2017-07-09 17:43:18.479', '$2a$10$tiMAYnret9YWUZrYs9R1YuQ8txI1XLW.zV4noh7F4Z9uS7gqBB/n2', 'kanth', 'Active'),
('kamal', NULL, TIMESTAMP '2017-07-09 17:43:43.732', 'kamal.hasan@hero.com', 'kamal', NULL, TIMESTAMP '2017-07-09 17:43:43.732', '$2a$10$KY4biHGriyKm91AW75sKMuctLIHQAqmkfCP6izSSzJn1m5JfrL7rO', 'hasan', 'Active'),
('satiyaraj', NULL, TIMESTAMP '2017-07-09 17:45:01.497', 'satiyaraj@hero.com', 'satiyaraj', NULL, TIMESTAMP '2017-07-09 17:45:01.497', '$2a$10$/llFk4cvIrtHVna3rRW9xOzYzBUJezBxHU4b5XXzXy7s9LH8lb6ra', 'm', 'Active'),
('vijay', NULL, TIMESTAMP '2017-07-09 17:45:21.27', 'vijay.c@hero.com', 'vijay', NULL, TIMESTAMP '2017-07-09 17:45:21.27', '$2a$10$8jnW4RNVIw4TKOg2SJ3n9OBDCZVk1gLATGokHmjxgew8QJS6CHtO2', 'c', 'Active'),
('ajith', NULL, TIMESTAMP '2017-07-09 17:45:43.292', 'ajith.kumar@hero.com', 'ajith', NULL, TIMESTAMP '2017-07-09 17:45:43.292', '$2a$10$RSy5RiH/Hn.ZFGMk.8c38.MQ22Cp5GjscXeB31iXmHJuUok4Xy2dC', 'kumar', 'Active'),
('teamMember1', NULL, TIMESTAMP '2017-07-09 18:20:14.964', 'Team.member.1@team.com', 'Team', NULL, TIMESTAMP '2017-07-09 18:20:14.964', '$2a$10$/LU/EIRp06jHxXH1dVucJONDcHiKNkHdVWGiS978SUL8Fek6eL2u6', 'Member1', 'Active'),
('teamMember2', NULL, TIMESTAMP '2017-07-09 18:20:15.218', 'Team.member.2@team.com', 'Team', NULL, TIMESTAMP '2017-07-09 18:20:15.218', '$2a$10$.QHnYiaeteianGYb/4Ue9Op0aQa1jgIrgqAdEdJZoIJvUmBYU5dN6', 'Member2', 'Active'),
('teamMember3', NULL, TIMESTAMP '2017-07-09 18:20:15.327', 'Team.member.3@team.com', 'Team', NULL, TIMESTAMP '2017-07-09 18:20:15.327', '$2a$10$wIk8MBjAxvBxYGrNHOmqqOOMDDVE6sJN1oyPDMu/9WxPGz81HhFOC', 'Member3', 'Active'),
('teamMember4', NULL, TIMESTAMP '2017-07-09 18:20:15.437', 'Team.member.4@team.com', 'Team', NULL, TIMESTAMP '2017-07-09 18:20:15.437', '$2a$10$R1b3qRq.j/lwaNSJSoCRC.XcBfKmd79bQrqJyE0rnUOdb3/ZS9eve', 'Member4', 'Active'),
('teamMember5', NULL, TIMESTAMP '2017-07-09 18:20:15.53', 'Team.member.5@team.com', 'Team', NULL, TIMESTAMP '2017-07-09 18:20:15.53', '$2a$10$Vd63jXyeEer.9HxeE6/ZT.OO.zi/N.D5thGWflk8O.cKoLehXocci', 'Member5', 'Active'),
('teamMember6', NULL, TIMESTAMP '2017-07-09 18:20:15.608', 'Team.member.6@team.com', 'Team', NULL, TIMESTAMP '2017-07-09 18:20:15.608', '$2a$10$U8v2UEV3gQod4YIzQ5UKe.91.NHggFolfDYhl0oTgKzZu9zZQb90C', 'Member6', 'Active'),
('teamMember7', NULL, TIMESTAMP '2017-07-09 18:20:15.702', 'Team.member.7@team.com', 'Team', NULL, TIMESTAMP '2017-07-09 18:20:15.702', '$2a$10$RchQbA5n.bja8dkmPNVCYedtaZWT1tpfKRTLqVO6G5.kkTie3WPL2', 'Member7', 'Active'),
('teamMember8', NULL, TIMESTAMP '2017-07-09 18:20:15.796', 'Team.member.8@team.com', 'Team', NULL, TIMESTAMP '2017-07-09 18:20:15.796', '$2a$10$t/VbRysjNXQKXrEDfOv/SObfTt1vUhR0MUEyzHAkC7OPDign21h4S', 'Member8', 'Active'),
('teamMember9', NULL, TIMESTAMP '2017-07-09 18:20:15.891', 'Team.member.9@team.com', 'Team', NULL, TIMESTAMP '2017-07-09 18:20:15.891', '$2a$10$3hYsE1z/A.Onr9dCx4wb6.Q689gjaDhy9prqS9e/tAbwg9zObbPLa', 'Member9', 'Active'),
('teamMember10', NULL, TIMESTAMP '2017-07-09 18:20:15.969', 'Team.member.10@team.com', 'Team', NULL, TIMESTAMP '2017-07-09 18:20:15.969', '$2a$10$ZogQDhsAfnXS0NQEQQo/ceDwfDsd8MYm2H.HLg.sJB1eHR8C/2CIm', 'Member10', 'Active'),
('teamMember11', NULL, TIMESTAMP '2017-07-09 18:20:16.047', 'Team.member.11@team.com', 'Team', NULL, TIMESTAMP '2017-07-09 18:20:16.047', '$2a$10$Wb9RmJ0mz7aFMNBryTq87.9MUL6j6E0Mt1T8f1HKyJln9nDpayix6', 'Member11', 'Active'),
('teamMember12', NULL, TIMESTAMP '2017-07-09 18:20:16.141', 'Team.member.12@team.com', 'Team', NULL, TIMESTAMP '2017-07-09 18:20:16.141', '$2a$10$9jHYY/ifBP6TvkGNc6NJv.FJwdjx4jECCoOgsFx4FekEJK1VhZVZ6', 'Member12', 'Active');      
INSERT INTO PUBLIC.USER(USER_ID, CREATED_BY, CREATED_DATE, EMAIL, FIRST_NAME, MODIFIED_BY, MODIFIED_DATE, PASSWORD, SECOND_NAME, STATE) VALUES
('teamMember13', NULL, TIMESTAMP '2017-07-09 18:20:16.219', 'Team.member.13@team.com', 'Team', NULL, TIMESTAMP '2017-07-09 18:20:16.219', '$2a$10$j4je7WedBAu9WqKDqK2Dne3PJGcITBPdQqyumqae2.T3NfSYoltZ6', 'Member13', 'Active'),
('teamMember14', NULL, TIMESTAMP '2017-07-09 18:20:16.313', 'Team.member.14@team.com', 'Team', NULL, TIMESTAMP '2017-07-09 18:20:16.313', '$2a$10$V5pfzjoxxsjccBzvf.vPQeKYhbLd9lkSo57Y31FJbH2Nb/LIKjlHK', 'Member14', 'Active'),
('teamMember15', NULL, TIMESTAMP '2017-07-09 18:20:16.391', 'Team.member.15@team.com', 'Team', NULL, TIMESTAMP '2017-07-09 18:20:16.391', '$2a$10$I7bZhp21vpfQCUXHdi8wuuHS7TgTfPbwWd7FUEkc/pPtkRzmArsjG', 'Member15', 'Active'),
('teamMember16', NULL, TIMESTAMP '2017-07-09 18:20:16.485', 'Team.member.16@team.com', 'Team', NULL, TIMESTAMP '2017-07-09 18:20:16.485', '$2a$10$cnJo.81SNfcChsnjAXzxku1tyS3WMAI8lE7/nMTIR.D4hQcQu1pqu', 'Member16', 'Active'),
('teamMember17', NULL, TIMESTAMP '2017-07-09 18:20:16.563', 'Team.member.17@team.com', 'Team', NULL, TIMESTAMP '2017-07-09 18:20:16.563', '$2a$10$yT9wOL3ovxVrYNwV0rcxEO4D7cjOW2hsnMf9h2/UjAcK1X2cba4Km', 'Member17', 'Active'),
('teamMember18', NULL, TIMESTAMP '2017-07-09 18:20:16.657', 'Team.member.18@team.com', 'Team', NULL, TIMESTAMP '2017-07-09 18:20:16.657', '$2a$10$Vq2GGWI1.7kXPoYO9Yzrn.PEaGDhae8PZIrOhomuO1/Ml9ip6Id1i', 'Member18', 'Active'),
('teamMember19', NULL, TIMESTAMP '2017-07-09 18:20:16.735', 'Team.member.19@team.com', 'Team', NULL, TIMESTAMP '2017-07-09 18:20:16.735', '$2a$10$u0BfsXpVlv9NU6DQmMAWSO5RoZFxSnGw7ADF8nN04bra4N2J4h/WW', 'Member19', 'Active'),
('teamMember20', NULL, TIMESTAMP '2017-07-09 18:20:16.813', 'Team.member.20@team.com', 'Team', NULL, TIMESTAMP '2017-07-09 18:20:16.813', '$2a$10$d9aXaY6NEWTHpRrL6hq0ne1bSbYgMjMVImYGi6AdN2col0gID7oYS', 'Member20', 'Active'),
('teamMember21', NULL, TIMESTAMP '2017-07-09 18:20:16.91', 'Team.member.21@team.com', 'Team', NULL, TIMESTAMP '2017-07-09 18:20:16.91', '$2a$10$2xp1XuRzohLVRoPdiAbShuP8GxGXbJW9ikKQw/T/j42ES/T4VlnmO', 'Member21', 'Active'),
('teamMember22', NULL, TIMESTAMP '2017-07-09 18:20:16.988', 'Team.member.22@team.com', 'Team', NULL, TIMESTAMP '2017-07-09 18:20:16.988', '$2a$10$gMtS/ULaEwpY2nnrnTj.wObylrahO3Nqebq2iEkeUeeAytRv.inDi', 'Member22', 'Active'),
('teamMember23', NULL, TIMESTAMP '2017-07-09 18:20:17.066', 'Team.member.23@team.com', 'Team', NULL, TIMESTAMP '2017-07-09 18:20:17.066', '$2a$10$ff9YsOdNcSfAIvzLBrYGTO421FmFItscmybwFx7ZohYaTpTE3/lva', 'Member23', 'Active'),
('teamMember24', NULL, TIMESTAMP '2017-07-09 18:20:17.16', 'Team.member.24@team.com', 'Team', NULL, TIMESTAMP '2017-07-09 18:20:17.16', '$2a$10$mdFZnSgzqZgo/dYLHpZQrehQbUCl8LEz1268o4r0S/oSLqOQ2rC2a', 'Member24', 'Active'),
('teamMember25', NULL, TIMESTAMP '2017-07-09 18:20:17.238', 'Team.member.25@team.com', 'Team', NULL, TIMESTAMP '2017-07-09 18:20:17.238', '$2a$10$V6.CPaI0MPUZHpcOzzM7MuCITDkRNC4qR8viGbPYx6YtHr1f2EoVq', 'Member25', 'Active'),
('teamMember26', NULL, TIMESTAMP '2017-07-09 18:20:17.316', 'Team.member.26@team.com', 'Team', NULL, TIMESTAMP '2017-07-09 18:20:17.316', '$2a$10$cqzU2nz9/vWdcuzXy98VKee7nlvEoXHDv2zRSb2zE2Tdbw5.ChXmO', 'Member26', 'Active'),
('teamMember27', NULL, TIMESTAMP '2017-07-09 18:20:17.41', 'Team.member.27@team.com', 'Team', NULL, TIMESTAMP '2017-07-09 18:20:17.41', '$2a$10$XJzePERCfj4FsqPSvFj8suPZV4TY81e2RrN5583NcT5jNPM0yroPO', 'Member27', 'Active'),
('teamMember28', NULL, TIMESTAMP '2017-07-09 18:20:17.488', 'Team.member.28@team.com', 'Team', NULL, TIMESTAMP '2017-07-09 18:20:17.488', '$2a$10$9sf1NF5YjldeBMvL0K1M2.HfT40oPjcQvFcUkbpyf2jM9aCL/w2EG', 'Member28', 'Active'),
('teamMember29', NULL, TIMESTAMP '2017-07-09 18:20:17.566', 'Team.member.29@team.com', 'Team', NULL, TIMESTAMP '2017-07-09 18:20:17.566', '$2a$10$WWpR32Sv5cPats/4pri21O6GgNf28eaCNIF3ue.H/eitnM43z00oS', 'Member29', 'Active'),
('teamMember30', NULL, TIMESTAMP '2017-07-09 18:20:17.66', 'Team.member.30@team.com', 'Team', NULL, TIMESTAMP '2017-07-09 18:20:17.66', '$2a$10$10p9oDymgCErTdAa5knBTevNG6OWTDAV3Nipv9eobGeg7/QJt0UdW', 'Member30', 'Active');    
           
INSERT INTO PUBLIC.USER_ROLE(ID, CREATED_BY, CREATED_DATE, MODIFIED_BY, MODIFIED_DATE, ROLE_CODE, USER_ID) VALUES
(1, 'vkarthik', TIMESTAMP '2017-07-09 18:01:49.176', 'vkarthik', TIMESTAMP '2017-07-09 18:01:49.176', 'ADMIN', 'vkarthik'),
(2, 'vkarthik', TIMESTAMP '2017-07-09 18:03:01.449', 'vkarthik', TIMESTAMP '2017-07-09 18:03:01.449', 'DEVELOPER', 'bbroko'),
(3, 'vkarthik', TIMESTAMP '2017-07-09 18:06:38.715', 'vkarthik', TIMESTAMP '2017-07-09 18:06:38.715', 'DEVELOPER', 'kamal'),
(4, 'vkarthik', TIMESTAMP '2017-07-09 18:07:04.154', 'vkarthik', TIMESTAMP '2017-07-09 18:07:04.154', 'DEVELOPER', 'rajini'),
(5, 'vkarthik', TIMESTAMP '2017-07-09 18:07:18.674', 'vkarthik', TIMESTAMP '2017-07-09 18:07:18.674', 'TEAM_LEAD', 'kamal'),
(6, 'vkarthik', TIMESTAMP '2017-07-09 18:07:24.871', 'vkarthik', TIMESTAMP '2017-07-09 18:07:24.871', 'TECH_LEAD', 'satiyaraj'),
(8, 'vkarthik', TIMESTAMP '2017-07-09 18:07:31.424', 'vkarthik', TIMESTAMP '2017-07-09 18:07:31.424', 'ARCHITECT', 'vijay'),
(9, 'vkarthik', TIMESTAMP '2017-07-09 18:07:42.521', 'vkarthik', TIMESTAMP '2017-07-09 18:07:42.521', 'PROJECT_MANAGER', 'ajith'),
(11, 'vkarthik', TIMESTAMP '2017-07-09 18:25:49.7', 'vkarthik', TIMESTAMP '2017-07-09 18:25:49.7', 'DEVELOPER', 'teamMember1'),
(12, 'vkarthik', TIMESTAMP '2017-07-09 18:28:10.796', 'vkarthik', TIMESTAMP '2017-07-09 18:28:10.796', 'DEVELOPER', 'teamMember2'),
(13, 'vkarthik', TIMESTAMP '2017-07-09 18:28:10.797', 'vkarthik', TIMESTAMP '2017-07-09 18:28:10.797', 'DEVELOPER', 'teamMember3'),
(14, 'vkarthik', TIMESTAMP '2017-07-09 18:28:10.797', 'vkarthik', TIMESTAMP '2017-07-09 18:28:10.797', 'DEVELOPER', 'teamMember4'),
(15, 'vkarthik', TIMESTAMP '2017-07-09 18:28:10.798', 'vkarthik', TIMESTAMP '2017-07-09 18:28:10.798', 'DEVELOPER', 'teamMember5'),
(16, 'vkarthik', TIMESTAMP '2017-07-09 18:28:10.798', 'vkarthik', TIMESTAMP '2017-07-09 18:28:10.798', 'DEVELOPER', 'teamMember6'),
(17, 'vkarthik', TIMESTAMP '2017-07-09 18:28:10.799', 'vkarthik', TIMESTAMP '2017-07-09 18:28:10.799', 'DEVELOPER', 'teamMember7'),
(18, 'vkarthik', TIMESTAMP '2017-07-09 18:28:10.799', 'vkarthik', TIMESTAMP '2017-07-09 18:28:10.799', 'DEVELOPER', 'teamMember8'),
(19, 'vkarthik', TIMESTAMP '2017-07-09 18:28:10.799', 'vkarthik', TIMESTAMP '2017-07-09 18:28:10.799', 'DEVELOPER', 'teamMember9'),
(20, 'vkarthik', TIMESTAMP '2017-07-09 18:28:10.8', 'vkarthik', TIMESTAMP '2017-07-09 18:28:10.8', 'DEVELOPER', 'teamMember10'),
(21, 'vkarthik', TIMESTAMP '2017-07-09 18:28:10.801', 'vkarthik', TIMESTAMP '2017-07-09 18:28:10.801', 'DEVELOPER', 'teamMember11'),
(22, 'vkarthik', TIMESTAMP '2017-07-09 18:28:10.801', 'vkarthik', TIMESTAMP '2017-07-09 18:28:10.801', 'DEVELOPER', 'teamMember12'),
(23, 'vkarthik', TIMESTAMP '2017-07-09 18:28:10.802', 'vkarthik', TIMESTAMP '2017-07-09 18:28:10.802', 'DEVELOPER', 'teamMember13'),
(24, 'vkarthik', TIMESTAMP '2017-07-09 18:28:10.802', 'vkarthik', TIMESTAMP '2017-07-09 18:28:10.802', 'DEVELOPER', 'teamMember14'),
(25, 'vkarthik', TIMESTAMP '2017-07-09 18:28:10.805', 'vkarthik', TIMESTAMP '2017-07-09 18:28:10.805', 'DEVELOPER', 'teamMember15'),
(26, 'vkarthik', TIMESTAMP '2017-07-09 18:28:10.805', 'vkarthik', TIMESTAMP '2017-07-09 18:28:10.805', 'DEVELOPER', 'teamMember16'),
(27, 'vkarthik', TIMESTAMP '2017-07-09 18:28:10.805', 'vkarthik', TIMESTAMP '2017-07-09 18:28:10.805', 'DEVELOPER', 'teamMember17'),
(28, 'vkarthik', TIMESTAMP '2017-07-09 18:28:10.806', 'vkarthik', TIMESTAMP '2017-07-09 18:28:10.806', 'DEVELOPER', 'teamMember18'),
(29, 'vkarthik', TIMESTAMP '2017-07-09 18:28:10.806', 'vkarthik', TIMESTAMP '2017-07-09 18:28:10.806', 'DEVELOPER', 'teamMember19'),
(30, 'vkarthik', TIMESTAMP '2017-07-09 18:28:10.806', 'vkarthik', TIMESTAMP '2017-07-09 18:28:10.806', 'DEVELOPER', 'teamMember20'),
(31, 'vkarthik', TIMESTAMP '2017-07-09 18:28:10.807', 'vkarthik', TIMESTAMP '2017-07-09 18:28:10.807', 'DEVELOPER', 'teamMember21'),
(32, 'vkarthik', TIMESTAMP '2017-07-09 18:28:10.807', 'vkarthik', TIMESTAMP '2017-07-09 18:28:10.807', 'DEVELOPER', 'teamMember22'),
(33, 'vkarthik', TIMESTAMP '2017-07-09 18:28:10.807', 'vkarthik', TIMESTAMP '2017-07-09 18:28:10.807', 'DEVELOPER', 'teamMember23');         
INSERT INTO PUBLIC.USER_ROLE(ID, CREATED_BY, CREATED_DATE, MODIFIED_BY, MODIFIED_DATE, ROLE_CODE, USER_ID) VALUES
(34, 'vkarthik', TIMESTAMP '2017-07-09 18:28:10.808', 'vkarthik', TIMESTAMP '2017-07-09 18:28:10.808', 'DEVELOPER', 'teamMember24'),
(35, 'vkarthik', TIMESTAMP '2017-07-09 18:28:10.808', 'vkarthik', TIMESTAMP '2017-07-09 18:28:10.808', 'DEVELOPER', 'teamMember25'),
(36, 'vkarthik', TIMESTAMP '2017-07-09 18:28:10.809', 'vkarthik', TIMESTAMP '2017-07-09 18:28:10.809', 'DEVELOPER', 'teamMember26'),
(37, 'vkarthik', TIMESTAMP '2017-07-09 18:28:10.809', 'vkarthik', TIMESTAMP '2017-07-09 18:28:10.809', 'DEVELOPER', 'teamMember27'),
(38, 'vkarthik', TIMESTAMP '2017-07-09 18:28:10.81', 'vkarthik', TIMESTAMP '2017-07-09 18:28:10.81', 'DEVELOPER', 'teamMember28'),
(39, 'vkarthik', TIMESTAMP '2017-07-09 18:28:10.81', 'vkarthik', TIMESTAMP '2017-07-09 18:28:10.81', 'DEVELOPER', 'teamMember29'),
(40, 'vkarthik', TIMESTAMP '2017-07-09 18:28:10.811', 'vkarthik', TIMESTAMP '2017-07-09 18:28:10.811', 'DEVELOPER', 'teamMember30');       


insert into project_status values('New');
insert into project_status values('Live');
insert into project_status values('Cancelled');
insert into project_status values('Rejected');
insert into project_status values('Completed');
insert into project_status values('Planned');
insert into project_status values('Elaboration');
insert into project_status values('Design');
insert into project_status values('Ready for Development');
insert into project_status values('In Development');

