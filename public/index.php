<?php

declare(strict_types=1);

use Psr\Http\Message\ResponseInterface as Response;
use Psr\Http\Message\ServerRequestInterface as Request;
use Slim\Factory\AppFactory;

require __DIR__ . '/../vendor/autoload.php';
require __DIR__ . '/../includes/menuOperations.php';
require __DIR__ . '/../includes/orderOperations.php';
require __DIR__ . '/../includes/employeeOperations.php';
require __DIR__ . '/../includes/customerOperations.php';
require __DIR__ . '/../includes/ingredientsOperations.php';
$app = AppFactory::create();

$app->addErrorMiddleware(true, true, false);

$app->add(new Tuupola\Middleware\HttpBasicAuthentication([
	"secure"=>false,
	"users"=> [
		"BIGKEV" => "3444_project_kkwh",
	]
]));

$app->post('/createMenuItem', function(Request $request, Response $response){
	if(!haveEmptyParameters(array('id', 'name', 'cost', 'descrip'), $request, $response)){
		$request_data = $request->getParsedBody();
		
		$id = $request_data['id'];
		$name = $request_data['name'];
		$cost = $request_data['cost'];
		$descrip = $request_data['descrip'];
		
		$db = new menuOperations;
		
		$result = $db->createItem($id, $name, $cost, $descrip);
		
		if($result == ITEM_CREATE){
			$message = array();
			$message['error'] = false;
			$message['message'] = 'success!';
			
			$response->getBody()->write(json_encode($message));
			
			return $response->withHeader('Content-type', 'application/json')->withStatus(201);
		}
			
		else if($result == ITEM_FAIL){
			$message = array();
			$message['error'] = true;
			$message['message'] = 'error occured';
			
			$response->getBody()->write(json_encode($message));
			
			return $response->withHeader('Content-type', 'application/json')->withStatus(422);			
		}
	}

	return $response->withHeader('Content-type', 'application/json')->withStatus(422);
});

$app->post('/createOrder', function(Request $request, Response $response){
	if(!haveEmptyParameters(array('tableNum', 'entree', 'side', 'drink', 'orderTotal'), $request, $response)){
		$request_data = $request->getParsedBody();
		
		$tableNum = $request_data['tableNum'];
		$entree = $request_data['entree'];
		$side = $request_data['side'];
		$drink = $request_data['drink'];
		$orderTotal = $request_data['orderTotal'];
		
		$db = new orderOperations;
		
		$result = $db->createOrder($tableNum, $entree, $side, $drink, $orderTotal);
		
		if($result == ORDER_CREATE){
			$message = array();
			$message['error'] = false;
			$message['message'] = 'success!';
			
			$response->getBody()->write(json_encode($message));
			
			return $response->withHeader('Content-type', 'application/json')->withStatus(201);
		}
			
		else if($result == ORDER_FAIL){
			$message = array();
			$message['error'] = true;
			$message['message'] = 'error occured';
			
			$response->getBody()->write(json_encode($message));
			
			return $response->withHeader('Content-type', 'application/json')->withStatus(422);			
		}
	}

	return $response->withHeader('Content-type', 'application/json')->withStatus(422);
});

$app->post('/createEmp', function(Request $request, Response $response){

    if(!haveEmptyParameters(array('password', 'name', 'wage', 'role', 'time'), $request, $response)){

        $request_data = $request->getParsedBody(); 

        $password = $request_data['password'];
        $name = $request_data['name'];
        $wage = $request_data['wage'];
        $role = $request_data['role'];
		$time = $request_data['time'];

        $hash_password = password_hash($password, PASSWORD_DEFAULT);

        $db = new employeeOperations; 

        $result = $db->createEmp($hash_password, $name, $wage, $role, $time);
        
        if($result == EMP_CREATE){

            $message = array(); 
            $message['error'] = false; 
            $message['message'] = 'Employee created successfully';

            $response->getBody()->write(json_encode($message));

            return $response
                        ->withHeader('Content-type', 'application/json')
                        ->withStatus(201);

        }else if($result == EMP_FAIL){

            $message = array(); 
            $message['error'] = true; 
            $message['message'] = 'Some error occurred';

            $response->getBody()->write(json_encode($message));

            return $response
                        ->withHeader('Content-type', 'application/json')
                        ->withStatus(422);    

        }
    }
    return $response
        ->withHeader('Content-type', 'application/json')
        ->withStatus(422);    
});

$app->post('/empLogin', function(Request $request, Response $response){

    if(!haveEmptyParameters(array('id', 'password'), $request, $response)){
        $request_data = $request->getParsedBody(); 

        $id = $request_data['id'];
        $password = $request_data['password'];
        
        $db = new employeeOperations; 

        $result = $db->login($id, $password);

        if($result == EMP_AUTHENTICATED){
            
            $response_data = array();

            $response_data['error']=false; 
            $response_data['message'] = 'Login Successful';

            $response->getBody()->write(json_encode($response_data));

            return $response
                ->withHeader('Content-type', 'application/json')
                ->withStatus(200);    

        }else if($result == EMP_NOT_FOUND){
            $response_data = array();

            $response_data['error']=true; 
            $response_data['message'] = 'Employeee not found';

            $response->getBody()->write(json_encode($response_data));

            return $response
                ->withHeader('Content-type', 'application/json')
                ->withStatus(200);    

        }else if($result == EMP_PASSWORD_FAIL){
            $response_data = array();

            $response_data['error']=true; 
            $response_data['message'] = 'Invalid credential';

            $response->getBody()->write(json_encode($response_data));

            return $response
                ->withHeader('Content-type', 'application/json')
                ->withStatus(200);  
        }
    }

    return $response
        ->withHeader('Content-type', 'application/json')
        ->withStatus(422);    
});


$app->get('/getItem/{id}', function(Request $request, Response $response, array $args){
		
	$id = $args['id'];
	$db = new menuOperations;
	
	$found = $db->itemExist($id);
	if($found){
		$item = $db->findItem($id);
		$message = array();
			
		$message['error'] = false;
		//$message['message'] = 'found data';
		$message['item'] = $item;
			
		$response->getBody()->write(json_encode($message));
		return $response->withHeader('Content-type', 'application/json')->withStatus(201);	
	}
	
	else{
		$message = array();
			
		$message['error'] = true;
		$message['message'] = 'no data found';
			
		$response->getBody()->write(json_encode($message));

		return $response->withHeader('Content-type', 'application/json')->withStatus(422);	
	}
});

$app->get('/getOrder/{id}', function(Request $request, Response $response, array $args){
		
	$id = $args['id'];
	$db = new orderOperations;
	
	$found = $db->orderExist($id);
	if($found){
		$order = $db->findOrder($id);
		$message = array();
			
		$message['error'] = false;
		//$message['message'] = 'found data';
		$message['order'] = $order;
			
		$response->getBody()->write(json_encode($message));
		return $response->withHeader('Content-type', 'application/json')->withStatus(201);	
	}
	
	else{
		$message = array();
			
		$message['error'] = true;
		$message['message'] = 'no data found';
			
		$response->getBody()->write(json_encode($message));

		return $response->withHeader('Content-type', 'application/json')->withStatus(422);	
	}
});

$app->get('/getEmp/{id}', function(Request $request, Response $response, array $args){
		
	$id = $args['id'];
	$db = new employeeOperations;
	
	$found = $db->empExist($id);
	if($found){
		$employee = $db->findEmployee($id);
		$message = array();
			
		$message['error'] = false;
		//$message['message'] = 'found data';
		$message['employee'] = $employee;
			
		$response->getBody()->write(json_encode($message));
		return $response->withHeader('Content-type', 'application/json')->withStatus(201);	
	}
	
	else{
		$message = array();
			
		$message['error'] = true;
		$message['message'] = 'no data found';
			
		$response->getBody()->write(json_encode($message));

		return $response->withHeader('Content-type', 'application/json')->withStatus(422);	
	}
});

$app->get('/getItems', function(Request $request, Response $response){
	$db = new menuOperations;
	$return_array = $db->allItems();
	
	$message = array();
	$message['error'] = false;
	$message['items'] = $return_array;
	
	$response->getBody()->write(json_encode($message));
	
	return $response->withHeader('Content-type', 'application/json')->withStatus(200);	
});

$app->get('/getOrders', function(Request $request, Response $response){
	$db = new orderOperations;
	$return_array = $db->allOrders();
	
	$message = array();
	$message['error'] = false;
	$message['orders'] = $return_array;
	
	$response->getBody()->write(json_encode($message));
	
	return $response->withHeader('Content-type', 'application/json')->withStatus(200);	
});

$app->get('/getAllEmp', function(Request $request, Response $response){
	$db = new employeeOperations;
	$return_array = $db->allEmp();
	
	$message = array();
	$message['error'] = false;
	$message['employees'] = $return_array;
	
	$response->getBody()->write(json_encode($message));
	
	return $response->withHeader('Content-type', 'application/json')->withStatus(200);	
});

$app->put('/updateItem/{id}', function(Request $request, Response $response, array $args){
	$id = $args['id'];

    if(!haveEmptyParameters(array('name','cost','descrip'), $request, $response)){

		$reqBody = file_get_contents('php://input');
        parse_str($reqBody, $request_data);
        $name = $request_data['name'];
        $cost = $request_data['cost'];
        $descrip = $request_data['descrip']; 

        $db = new menuOperations; 

        if($db->updateitem($id, $name, $cost, $descrip)){
            $response_data = array(); 
            $response_data['error'] = false; 
            $response_data['message'] = 'item Updated Successfully';
            $item = $db->findItem($id);
            $response_data['item'] = $item; 

            $response->getBody()->write(json_encode($response_data));

            return $response
            ->withHeader('Content-type', 'application/json')
            ->withStatus(200);  
        
        }else{
            $response_data = array(); 
            $response_data['error'] = true; 
            $response_data['message'] = 'Please try again later';
            $item = $db->findItem($id);
            $response_data['item'] = $item; 

            $response->getBody()->write(json_encode($response_data));

            return $response
            ->withHeader('Content-type', 'application/json')
            ->withStatus(200);  
              
        }

    }
    
    return $response
    ->withHeader('Content-type', 'application/json')
    ->withStatus(200);	
});

$app->put('/changeOrder/{id}', function(Request $request, Response $response, array $args){
	$id = $args['id'];

    if(!haveEmptyParameters(array('tableNum','entree','side', 'drink', 'orderTotal'), $request, $response)){

		$reqBody = file_get_contents('php://input');
        parse_str($reqBody, $request_data);
		
        $tableNum = $request_data['tableNum'];
        $entree = $request_data['entree'];
        $side = $request_data['side']; 
		$drink = $request_data['drink'];
		$orderTotal = $request_data['orderTotal'];

        $db = new orderOperations; 

        if($db->updateOrder($id, $tableNum, $entree, $drink, $side, $orderTotal)){
            $response_data = array(); 
            $response_data['error'] = false; 
            $response_data['message'] = 'Order Updated Successfully';
            $order = $db->findOrder($id);
            $response_data['order'] = $order; 

            $response->getBody()->write(json_encode($response_data));

            return $response
            ->withHeader('Content-type', 'application/json')
            ->withStatus(200);  
        
        }else{
            $response_data = array(); 
            $response_data['error'] = true; 
            $response_data['message'] = 'Please try again later';
            $order = $db->findItem($id);
            $response_data['order'] = $order; 

            $response->getBody()->write(json_encode($response_data));

            return $response
            ->withHeader('Content-type', 'application/json')
            ->withStatus(200);  
              
        }

    }
    
    return $response
    ->withHeader('Content-type', 'application/json')
    ->withStatus(200);	
});

$app->put('/updateEmp/{id}', function(Request $request, Response $response, array $args){

    $id = $args['id'];

    if(!haveEmptyParameters(array('name','wage','role', 'time'), $request, $response)){

		$reqBody = file_get_contents('php://input');
        parse_str($reqBody, $request_data);
		
        $name = $request_data['name'];
        $wage = $request_data['wage'];
		$role = $request_data['role'];
		$time = $request_data['time'];

        $db = new employeeOperations; 

        if($db->updateEmployee($name, $wage, $role, $time, $id)){
            $response_data = array(); 
            $response_data['error'] = false; 
            $response_data['message'] = 'Employee Updated Successfully';
            $employee = $db->findEmployee($id);
            $response_data['employee'] = $employee; 

            $response->getBody()->write(json_encode($response_data));

            return $response
            ->withHeader('Content-type', 'application/json')
            ->withStatus(200);  
        
        }else{
            $response_data = array(); 
            $response_data['error'] = true; 
            $response_data['message'] = 'Please try again later';
            $employee = $db->getUserByEmail($id);
            $response_data['employee'] = $employee; 

            $response->getBody()->write(json_encode($response_data));

            return $response
            ->withHeader('Content-type', 'application/json')
            ->withStatus(200);  
        }

    }
    
    return $response
    ->withHeader('Content-type', 'application/json')
    ->withStatus(200);  

});

$app->delete('/deleteItem/{id}', function(Request $request, Response $response, array $args){
    $id = $args['id'];

    $db = new menuOperations; 

    $response_data = array();

    if($db->deleteItem($id)){
        $response_data['error'] = false; 
        $response_data['message'] = 'Item has been deleted';    
    }else{
        $response_data['error'] = true; 
        $response_data['message'] = 'Plase try again later';
    }

    $response->getBody()->write(json_encode($response_data));

    return $response
    ->withHeader('Content-type', 'application/json')
    ->withStatus(200);
});

$app->delete('/deleteOrder/{id}', function(Request $request, Response $response, array $args){
    $id = $args['id'];

    $db = new orderOperations; 

    $response_data = array();

    if($db->deleteOrder($id)){
        $response_data['error'] = false; 
        $response_data['message'] = 'Order has been deleted';    
    }else{
        $response_data['error'] = true; 
        $response_data['message'] = 'Plase try again later';
    }

    $response->getBody()->write(json_encode($response_data));

    return $response
    ->withHeader('Content-type', 'application/json')
    ->withStatus(200);
});

$app->delete('/clearOrderQueue', function(Request $request, Response $response){
	$db = new orderOperations;
	
	$response_data = array();
	
	if($db->clearQueue()){
		$response_data['error'] = false; 
        $response_data['message'] = 'clear';
	}
	
	else{
        $response_data['error'] = true; 
        $response_data['message'] = 'Plase try again later';
	}
    return $response
    ->withHeader('Content-type', 'application/json')
    ->withStatus(200);
});

$app->delete('/deleteEmployee/{id}', function(Request $request, Response $response, array $args){
    $id = $args['id'];

    $db = new employeeOperations; 

    $response_data = array();

    if($db->deleteEmployee($id)){
        $response_data['error'] = false; 
        $response_data['message'] = 'Employee has been deleted';    
    }else{
        $response_data['error'] = true; 
        $response_data['message'] = 'Plase try again later';
    }

    $response->getBody()->write(json_encode($response_data));

    return $response
    ->withHeader('Content-type', 'application/json')
    ->withStatus(200);
});


$app->post('/createcustomer', function(Request $request, Response $response){

    if(!haveEmptyParameters(array('phone', 'name', 'password', 'birthday','visited', 'credits'), $request, $response)){

        $request_data = $request->getParsedBody();

        $phone = $request_data['phone'];
        $name = $request_data['name'];
        $password = $request_data['password'];
        $birthday = $request_data['birthday'];
        $visited = $request_data['visited'];
        $credits = $request_data['credits'];

        $hash_password = password_hash($password, PASSWORD_DEFAULT);

        $db = new DbOperations;

        $result = $db->createCustomer($phone, $name, $hash_password, $birthday, $visited, $credits);

        if($result == USER_CREATED){

            $message = array();
            $message['error'] = false;
            $message['message'] = 'Customer created successfully';

            $response->getBody()->write(json_encode($message));

            return $response
                        ->withHeader('Content-type', 'application/json')
                        ->withStatus(201);

        }else if($result == USER_FAILURE){

            $message = array();
            $message['error'] = true;
            $message['message'] = 'Some error occurred';

            $response->getBody()->write(json_encode($message));

            return $response
                        ->withHeader('Content-type', 'application/json')
                        ->withStatus(422);

        }else if($result == USER_EXISTS){
            $message = array();
            $message['error'] = true;
            $message['message'] = 'Customer Already Exists';

            $response->getBody()->write(json_encode($message));

            return $response
                        ->withHeader('Content-type', 'application/json')
                        ->withStatus(422);
        }
    }
    return $response
        ->withHeader('Content-type', 'application/json')
        ->withStatus(422);
});

$app->post('/customerlogin', function(Request $request, Response $response){

    if(!haveEmptyParameters(array('phone', 'password'), $request, $response)){
        $request_data = $request->getParsedBody();

        $phone = $request_data['phone'];
        $password = $request_data['password'];

        $db = new DbOperations;

        $result = $db->customerLogin($phone, $password);

        if($result == USER_AUTHENTICATED){

            $customer = $db->getCustomerByPhone($phone);
            $response_data = array();

            $response_data['error']=false;
            $response_data['message'] = 'Login Successful';
            $response_data['customer']=$customer;

            $response->getBody()->write(json_encode($response_data));

            return $response
                ->withHeader('Content-type', 'application/json')
                ->withStatus(200);

        }else if($result == USER_NOT_FOUND){
            $response_data = array();

            $response_data['error']=true;
            $response_data['message'] = 'Customer not exist';

            $response->getBody()->write(json_encode($response_data));

            return $response
                ->withHeader('Content-type', 'application/json')
                ->withStatus(200);

        }else if($result == USER_PASSWORD_DO_NOT_MATCH){
            $response_data = array();

            $response_data['error']=true;
            $response_data['message'] = 'Invalid credential';

            $response->getBody()->write(json_encode($response_data));

            return $response
                ->withHeader('Content-type', 'application/json')
                ->withStatus(200);
        }
    }

    return $response
        ->withHeader('Content-type', 'application/json')
        ->withStatus(422);
});

$app->get('/allcustomers', function(Request $request, Response $response){

    $db = new DbOperations;

    $customers = $db->getAllCustomer();

    $response_data = array();

    $response_data['error'] = false;
    $response_data['customers'] = $customers;

    $response->getBody()->write(json_encode($response_data));

    return $response
    ->withHeader('Content-type', 'application/json')
    ->withStatus(200);

});

$app->put('/updatecustomer/{id}', function(Request $request, Response $response, array $args){

    $id = $args['id'];

    if(!haveEmptyParameters(array('phone', 'name', 'birthday','visited', 'credits'), $request, $response)){


        $reqBody = file_get_contents('php://input');
        parse_str($reqBody, $request_data);

        $phone = $request_data['phone'];
        $name = $request_data['name'];
        $password = $request_data['password'];
        $birthday = $request_data['birthday'];
        $visited = $request_data['visited'];
        $credits = $request_data['credits'];


        $db = new DbOperations;

        if($db->updateCustomer($phone, $name, $birthday, $visited, $credits, $id)){
            $response_data = array();
            $response_data['error'] = false;
            $response_data['message'] = 'Customer Updated Successfully';
            $customer = $db->getCustomerByPhone($phone);
            $response_data['customer'] = $customer;

            $response->getBody()->write(json_encode($response_data));

            return $response
            ->withHeader('Content-type', 'application/json')
            ->withStatus(200);

        }else{
            $response_data = array();
            $response_data['error'] = true;
            $response_data['message'] = 'Please try again later';
            $customer = $db->getCustomerByPhone($phone);
            $response_data['customer'] = $customer;

            $response->getBody()->write(json_encode($response_data));

            return $response
            ->withHeader('Content-type', 'application/json')
            ->withStatus(200);

        }

    }

    return $response
    ->withHeader('Content-type', 'application/json')
    ->withStatus(200);

});

$app->put('/updateCustomerPassword', function(Request $request, Response $response){

    if(!haveEmptyParameters(array('currentpassword', 'newpassword', 'phone'), $request, $response)){

         $reqBody = file_get_contents('php://input');
        parse_str($reqBody, $request_data);

        $currentpassword = $request_data['currentpassword'];
        $newpassword = $request_data['newpassword'];
        $phone = $request_data['phone'];

        $db = new DbOperations;

        $result = $db->updatePassword($currentpassword, $newpassword, $phone);

        if($result == PASSWORD_CHANGED){
            $response_data = array();
            $response_data['error'] = false;
            $response_data['message'] = 'Password Changed';
            $response->getBody()->write(json_encode($response_data));
            return $response->withHeader('Content-type', 'application/json')
                            ->withStatus(200);

        }else if($result == PASSWORD_DO_NOT_MATCH){
            $response_data = array();
            $response_data['error'] = true;
            $response_data['message'] = 'You have given wrong password';
            $response->getBody()->write(json_encode($response_data));
            return $response->withHeader('Content-type', 'application/json')
                            ->withStatus(200);
        }else if($result == PASSWORD_NOT_CHANGED){
            $response_data = array();
            $response_data['error'] = true;
            $response_data['message'] = 'Some error occurred';
            $response->getBody()->write(json_encode($response_data));
            return $response->withHeader('Content-type', 'application/json')
                            ->withStatus(200);
        }
    }

    return $response
        ->withHeader('Content-type', 'application/json')
        ->withStatus(422);
});

$app->delete('/deleteuser/{id}', function(Request $request, Response $response, array $args){
    $id = $args['id'];

    $db = new DbOperations;

    $response_data = array();

    if($db->deleteCustomer($id)){
        $response_data['error'] = false;
        $response_data['message'] = 'User has been deleted';
    }else{
        $response_data['error'] = true;
        $response_data['message'] = 'Plase try again later';
    }

    $response->getBody()->write(json_encode($response_data));

    return $response
    ->withHeader('Content-type', 'application/json')
    ->withStatus(200);
});

$app->post('/createingredient', function(Request $request, Response $response){

    if(!haveEmptyParameters(array('food', 'amount'), $request, $response)){

		//$reqBody = file_get_contents('php://input');
		//parse_str($reqBody, $request_params);
		$request_data = $request->getParsedBody();

        $food = $request_data['food'];
        $amount = $request_data['amount'];


        //$hash_password = password_hash($password, PASSWORD_DEFAULT);

        $db = new ingredientsOperations;

        $result = $db->createFood($food, $amount);

        if($result == ING_CREATE){

            $message = array();
            $message['error'] = false;
            $message['message'] = 'Ingredient created successfully';

            $response->getbody()->write(json_encode($message));

            return $response
                        ->withHeader('Content-type', 'application/json')
                        ->withStatus(201);

        }else if($result == ING_FAIL){

            $message = array();
            $message['error'] = true;
            $message['message'] = 'Some error occurred';

            $response->getbody()->write(json_encode($message));

            return $response
                        ->withHeader('Content-type', 'application/json')
                        ->withStatus(422);

        }else if($result == ING_EXIST){
            $message = array();
            $message['error'] = true;
            $message['message'] = 'Ingredient Already Exists';

            $response->getbody()->write(json_encode($message));

            return $response
                        ->withHeader('Content-type', 'application/json')
                        ->withStatus(422);
        }
    }
    return $response
        ->withHeader('Content-type', 'application/json')
		->withStatus(422);
	
});

$app->get('/allingredients', function(Request $request, Response $response){

	$db = new ingredientsOperations;

	$ingts = $db->getAllIngredients();

	$response_data = array();

	$response_data['error'] = false;
	$response_data['ingts'] = $ingts;

	$response->getbody()->write(json_encode($response_data));

	return $response
        ->withHeader('Content-type', 'application/json')
		->withStatus(200);

});

$app->put('/updateIngredient/{id}', function(Request $request, Response $response, array $args){

	$id = $args['id'];

	if(!haveEmptyParameters(array('food', 'amount'), $request, $response)){

		$reqBody = file_get_contents('php://input');
		parse_str($reqBody, $request_data);
		//$request_data = $request->getParsedBody();
		//$id = $request_data['id'];
		$food = $request_data['food'];
		$amount = $request_data['amount'];
		
		$db = new ingredientsOperations;

		if($db->updateIngredients($food, $amount, $id)){
			$response_data = array();
			$response_data['error'] = false;
			$response_data['message'] = 'Update Successful';
			$ing = $db->getFoodByName($food);
			$response_data['ing'] = $ing;

			$response->getbody()->write(json_encode($response_data));

			return $response
        		->withHeader('Content-type', 'application/json')
				->withStatus(200);

		}else{
			$response_data = array();
			$response_data['error'] = true;
			$response_data['message'] = 'Try Again';
			$ing = $db->getFoodByName($food);
			$response_data['ing'] = $ing;

			$response->getbody()->write(json_encode($response_data));

			return $response
        		->withHeader('Content-type', 'application/json')
				->withStatus(200);
		}

	}

	return $response
        ->withHeader('Content-type', 'application/json')
		->withStatus(200);

});

$app->delete('/trashfood/{id}', function(Request $request, Response $response, array $args){
	$id = $args['id'];

	$db = new ingredientsOperations;

	$response_data = array();

	if($db->trashedFood($id)){
		$response_data['error'] = false;
		$response_data['message'] = 'Food Thrown Out!!';
	}else{
		$response_data['error'] = true;
		$response_data['message'] = 'Error';
	}

	$response->getbody()->write(json_encode($response_data));

	return $response
        		->withHeader('Content-type', 'application/json')
				->withStatus(200);

});


function haveEmptyParameters($required_params, $request, $response){
    $error = false; 
    $error_params = '';
    $reqBody = file_get_contents('php://input');
    parse_str($reqBody, $request_params);

    foreach($required_params as $param){
        if(!isset($request_params[$param]) || strlen($request_params[$param])<=0){
            $error = true; 
            $error_params .= $param . ', ';
        }
    }

    if($error){
        $error_detail = array();
        $error_detail['error'] = true; 
        $error_detail['message'] = 'Required parameters ' . substr($error_params, 0, -2) . ' are missing or empty';
        $response->getBody()->write(json_encode($error_detail));
    }
    return $error; 
}


$app->run();

