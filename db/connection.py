import mysql.connector 
from mysql.connector import Error

class DBconnection:
    @staticmethod 
    def get_connection():
        try:
            conn = mysql.connector.connect(
                host='localhost',
                user='StudentsDB',
                password='kani@2004',
                database='StudentsDB'
            )
            if conn.is_connected():
                return conn
        except Error as e:
            raise Exception(f"DB connection error: {e}")
