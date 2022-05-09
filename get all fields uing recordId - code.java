Id sObjectId = '0012v00003MTI8xAAH';
String objectName = sObjectId.getSObjectType().getDescribe().getName();
Schema.SObjectType sObjectTypeObj = Schema.getGlobalDescribe().get(objectName);
Schema.DescribeSObjectResult describeSObjectResultObj = sObjectTypeObj.getDescribe();
Map<String, Schema.SObjectField> fieldSetObjMap = describeSObjectResultObj.fields.getMap();
List<Schema.SObjectField> fldObjMapValuesList = fieldSetObjMap.values();
String queryString= 'SELECT ';
for( Schema.SObjectField s : fldObjMapValuesList ) {
    String fieldName = s.getDescribe().getName();
    queryString += fieldName + ', ';
}
queryString = queryString.removeEnd(', ');
queryString += ' FROM '+ objectName;
queryString += ' WHERE ' + 'Id = ' + '\'' + String.escapeSingleQuotes(sObjectId) + '\'';
List<sObject> queryResult = Database.query(queryString);