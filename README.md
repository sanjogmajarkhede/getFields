# How to fetch fields from any object using recordID in apex:

### Step 1: Fetch object name:
First, We need to know which object the record Id is referring to. We will use the object name to get the fields for that object and in the query to get the values for the fields.

**For that, store the recordId in a variable of type Id so that the variable can be used to perform necessary operations.**
```
Id  recordId  = “<<AnyRecordId>>”
```

**Use below code to get the object name for that record**    
```
String objectName = recordId.getSObjectType().getDescribe().getName();
```
	
### Step 2: Get all fields for that object using object name:
**Use below code:**
```
Schema.SObjectType sObjectTypeObj = Schema.getGlobalDescribe().get(objectName);
Schema.DescribeSObjectResult describeSObjectResultObj = sObjectTypeObj.getDescribe();
Map<String, Schema.SObjectField> fieldSetObjMap = describeSObjectResultObj.fields.getMap();
List<Schema.SObjectField> fldObjMapValuesList = fieldSetObjMap.values();
```
**fldObjMapValuesList will contain all field names for that object**
	
### Step 3: Query all fields for that object
**Use below code to generate dynamic query**
```
String queryString= 'SELECT ';
for( Schema.SObjectField s : fldObjMapValuesList ) {
 String fieldName = s.getDescribe().getName();
 queryString += fieldName + ', ';
}
queryString = queryString.removeEnd(', ');
queryString += ' FROM '+ objectName;
```
**Apply the filter for the recordId**
```
queryString += ' WHERE ' + 'Id = ' + '\'' + String.escapeSingleQuotes(sObjectId) + '\'';
```
**Query the field values for that record using Database.query(queryString);**	
