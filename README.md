# MapReduce XML parsing
#### First Load the code from eclipse
### Step 1:
Create jar file 
### Step 2:
#### Load Xml file into HDFS
#### Move this Text file to Cluster node
First you open terminal and split the two terminal on same windows 
One is Local System another one is Cluster node
### Step 3:
#### First Create Directory in Cluster node
Go to Cluster terminal
#### Login to Cluster node
ssh root@192.168.13.11
#### Create Directory
mkdir bigdata
### Step 4:
#### Move file from Local to Cluster node
Go to Local terminal
Give Permission
sudo -s
#### Move text file from cluster
scp -r /home/venkat/Desktop/Bigdatatraining/file.xml root@192.168.13.11:/root/bigdata
#### Move jar file from cluster
scp /home/venkat/Desktop/Bigdatatraining/MapR.jar root@192.168.13.11:/root/bigdata
### Step 5:
#### Move file from  Cluster node to HDFS
#### Go to Cluster terminal
#### Move text file from cluster to HDFS
hdfs dfs -put root/bigdata/file.xml /user/BigData
### Step 6:
#### Run jar file from cluster to HDFS
(hadoop jar jarfilename.jar packageName.ClassName  PathToInputTextFile PathToOutputDirectry)

hadoop jar bigdata/XmlMR.jar com.xmlparser.XMLDriver /user/BigData/file.xml /user/BigData/out
### Step 7:
#### View the Out file Folder
hadoop fs -ls /user/BigData/out
### Step 8:
#### View the Output file
hadoop fs -cat /user/BigData/out/part-r-00000
## End..
