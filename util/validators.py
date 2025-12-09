def validate_name(name):
    if not name or not isinstance(name,str) or len(name.strip())<2:
        raise ValueError("Invalid name")

def validate_age(age):
    if age is None:
        raise ValueError("Age required")
    try :
        age = int(age)
    except:
        raise ValueError("age must be integer")
    if age < 15 or age > 100:
        raise ValueError("Age out of allowed range (15-100)")

def validate_department(department):
    if not dept or len(dept.strip())<2:
        raise ValueError("Invalid department")    