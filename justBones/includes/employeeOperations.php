<?php

	class employeeOperations{
		private $con;
		
		function __construct(){
			require_once dirname(__FILE__) . '/DbConnect.php';
			$db = new DbConnect;
			$this->con = $db->connect();
		}
		
		public function createEmp($password, $name, $wage, $role, $time){
			$state = $this->con->prepare("INSERT INTO employee (password, name, wage, role, time) VALUES (?, ?, ?, ?, ?)");
			
			$state->bind_param("ssdsd", $password, $name, $wage, $role, $time);
			
			if($state->execute()){
				return EMP_CREATE;
			}
			else{
				return EMP_FAIL;
			}			
		}
		
		public function login($id, $password){
            if($this->empExist($id)){
                $hashed_password = $this->getPass($id); 
                if(password_verify($password, $hashed_password)){
                    return EMP_AUTHENTICATED;
                }else{
                    return EMP_PASSWORD_FAIL; 
                }
            }else{
                return EMP_NOT_FOUND; 
            }
        }
		
        private function getPass($id){
            $stmt = $this->con->prepare("SELECT password FROM employee WHERE id = ?");
            $stmt->bind_param("i", $id);
            $stmt->execute(); 
            $stmt->bind_result($password);
            $stmt->fetch(); 
            return $password; 
        }
		
		
		public function allEmp(){
			$state = $this->con->prepare("SELECT id, name, wage, role, time FROM employee");
			$state->execute();
			$state->bind_result($id, $name, $wage, $role, $time);
			
			$employees = array();
			while($state->fetch()){
				$employee = array();
				$employee['id'] = $id;
				$employee['name'] = $name;
				$employee['wage'] = $wage;
				$employee['role'] = $role;
				$employee['time'] = $time;
				array_push($employees, $employee);
			}
			return $employees;
		}
		
		public function findEmployee($id){
			$state = $this->con->prepare("SELECT id, name, wage, role, time FROM employee WHERE id = ?");
			$state->bind_param("i", $id);
			$state->execute();
			$state->bind_result($id, $name, $wage, $role, $time);
			$state->fetch();
			$employee = array();
			$employee['id'] = $id;
			$employee['name'] = $name;
			$employee['wage'] = $wage;
			$employee['role'] = $role;
			$employee['time'] = $time;
			return $employee;
		}
		
		public function empExist($id){
			$state = $this->con->prepare("SELECT * FROM employee WHERE id = ?");
			$state->bind_param("i", $id);
			$state->execute();
			$state->store_result();
			if ($state->num_rows > 0) return true;
			return false;
		}
		
		public function updateEmployee($name, $wage, $role, $time, $id){
            $state = $this->con->prepare("UPDATE employee SET name = ?, wage = ?, role = ?, time = ? WHERE id = ?");
            $state->bind_param("sdsdi", $name, $wage, $role, $time, $id);
            if($state->execute())
                return true;
            return false; 
		}
		
        public function deleteEmployee($id){
            $state = $this->con->prepare("DELETE FROM employee WHERE id = ?");
            $state->bind_param("i", $id);
            if($state->execute())
                return true; 
            return false; 
        }
	}