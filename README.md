# Scheduler
Scheduling program for teacher-student meetings during the Covid-19 pandemic at Proctor Elementary School in Castro Valley, CA.

This program is written in Java and addresses the needs that arose for teachers in Special Education during the Covid-19 Pandemic. 
Scheduling with hybrid learning became a challenge that added a significant number of hours to their already increased workload. 
This program reduced the time spent on scheduling manually to less than 5 minutes.

Format: Name,Grade,Total Minutes,Groupable,[Day1, section1, Day2, section2, …],[G1,name1,G2,name2,...]

**NO SPACES between commas**

Key: 
M - Monday
T - Tuesday
W - Wednesday
R - Thursday
F - Friday 

Groupable: 1 yes, 0 no 
(Day,Section) pairs describe when the student is unavailable.
(G,name) pairs describe which students that student must be grouped with.

Sections: 0-13 correspond to 30 minute sections 8 AM - 3 PM (14 total sections)
2:30-3:00   13
2:00-2:30   12
1:30-2:00   11
1:00-1:30   10
12:30-1:00   9
12:00-12:30  8
11:30-12:00  7
Etc.

Example: Bob F,3,150,1,M,7,T,11,John B,Kyle H
Bob F, grade 3, 150 minutes total to be seen that week, Is groupable,
unavailable Monday 11:30-12, Tuesday 1:30-2, Must be grouped with John B and Kyle H.

Sample input from one of the weeks used by teachers:
Liam H,3,150,1,W,11,M,12,T,11,G,Noah,G,Connor
Connor,3,180,1,M,12,T,11,W,10,G,Noah,G,Liam H
Noah,2,90,1,M,10,T,9,R,12,G,Connor,G,Liam H
Gideon,4,120,1,M,11,W,11,R,10,R,11
Valeria,5,120,1,R,10,R,11,R,12,T,10
Julie,5,90,0,R,10,R,11,R,12,T,10
Rhiley,5,150,1,R,10,R,11,R,12,T,10
Patrick,5,120,1,R,10,R,11,R,12,T,10
Sophia,2,120,1,M,10,T,9,R,12
Jake,4,120,1,M,11,W,11,R,10,R,11,G,Ethan
Ethan,4,180,1,M,11,W,11,T,10,T,11,G,Jake
Brandon,5,90,1,T,10,R,10,R,11,R,12
Teoni,2,150,1,M,10,T,10,R,12,G,Melody
Liam Wong Zelaya,5,150,1,T,10,R,10,R,11,R,12
Melody,3,120,1,G,Teoni,1,M,12,T,11,W,10
