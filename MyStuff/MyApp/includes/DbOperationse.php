<?php

    class DbOperationse{

        private $con;

        function __construct(){
            require_once dirname(__FILE__) . '/DbConnect.php';
            $db = new DbConnect;
            $this->con = $db->connect();
        }

        public function createEmployee($name, $password, $position, $wage){
            if(!$this->isEmployeeExist($name)){
                 $stmt = $this->con->prepare("INSERT INTO employees (name, password, position, wage) VALUES (?, ?, ?, ?)");
                 $stmt->bind_param("sssd", $name, $password, $position, $wage);
                 if($stmt->execute()){
                     return ITEM_CREATED;
                 }else{
                     return ITEM_FAILURE;
                 }
            }
            return ITEM_EXISTS;
         }

         public function employeeLogin($name, $password){
             if($this->isEmployeeExist($name)){
                 $hashed_password = $this->getUsersPasswordByName($name);
                 if(password_verify($password, $hashed_password)){
                     return USER_AUTHENTICATED;
                 }else{
                     return USER_PASSWORD_DO_NOT_MATCH;
                 }

             } else {
                 return USER_NOT_FOUND;
             }
             
         }

         private function getUsersPositionByName($name){
             $stmt = $this->con->prepare("SELECT position FROM employees WHERE name = ?");
             $stmt->bind_param("s", $name);
             $stmt->execute();
             $stmt->bind_result($position);
             $stmt->fetch();
             return $position;
         }

         public function getUsersPasswordByName($name){
            $stmt = $this->con->prepare("SELECT password FROM employees WHERE name = ?");
            $stmt->bind_param("s", $name);
            $stmt->execute();
            $stmt->bind_result($password);
            $stmt->fetch();
            return $password;
        }

        public function getAllEmployees(){
            $stmt = $this->con->prepare("SELECT id, name, position, wage FROM employees");
            $stmt->execute();
            $stmt->bind_result($id, $name, $position, $wage);
            $emps = array();
            while($stmt->fetch()){
                $emp = array();
                $emp['id'] = $id;
                $emp['name'] = $name;
                $emp['position'] = $position;
                $emp['wage'] = $wage;
                array_push($emps, $emp);
            }
            return $emps;
        }

         public function getUserByName($name){
            $stmt = $this->con->prepare("SELECT id, name, position, wage FROM employees WHERE name = ?");
            $stmt->bind_param("s", $name);
            $stmt->execute();
            $stmt->bind_result($id, $name, $position, $wage);
            $stmt->fetch();
            $emp = array();
            $emp['id'] = $id;
            $emp['name'] = $name;
            $emp['position'] = $position;
            $emp['wage'] = $wage;
            return $emp;
        }

        public function updateEmployees($name, $position, $wage, $id){
            $stmt = $this->con->prepare("UPDATE employees SET name = ?, position = ?, wage = ? WHERE id = ?");
            $stmt->bind_param("ssii", $name, $position, $wage, $id);
            if($stmt->execute())
               return true;
           return false;
        }

        public function updatePassword($currentpassword, $newpassword, $name){
            $hashed_password = $this->getUsersPasswordByName($name);

            if(password_verify($currentpassword, $hashed_password)){
                $hash_password = password_hash($newpassword, PASSWORD_DEFAULT);
                $stmt = $this->con->prepare("UPDATE employees SET password = ? WHERE name = ?");
                $stmt->bind_param("ss", $hash_password, $name);

                if($stmt->execute())
                    return PASSWORD_CHANGED;
                return PASSWORD_NOT_CHANGED;
            }else{
                return PASSWORD_DO_NOT_MATCH;
            }
        }

        public function fireEmployee($id){
            $stmt = $this->con->prepare("DELETE FROM employees WHERE id = ?");
            $stmt->bind_param("i", $id);
            if($stmt->execute())
                return true;
            return false;
        }

         private function isEmployeeExist($name){
            //$stmt = $this->con->prepare("SELECT id FROM ingredient WHERE food = ?");
            $stmt = $this->con->prepare("SELECT id FROM employees WHERE name = ?");
            $stmt->bind_param("s", $name);
            $stmt->execute();
            $stmt->store_result();
            return $stmt->num_rows > 0;
        }

    }