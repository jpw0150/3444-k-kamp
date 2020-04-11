<?php


use Psr\Http\Message\ResponseInterface as Response;
use Psr\Http\Message\ServerRequestInterface as Request;
use Slim\Factory\AppFactory;

require __DIR__ . '/../vendor/autoload.php';
require __DIR__ . '/../includes/DbOperations.php';
require __DIR__ . '/../includes/DbOperationse.php';

$app = AppFactory::create();
$app->addRoutingMiddleware();
$errorMiddleware = $app->addErrorMiddleware(true, true, true);

/*
	endpoint: createfood
	parameters: food, amount
	method: POST
*/

$app->post('/MyApp/public/createingredient', function(Request $request, Response $response){

    if(!haveEmptyParameters(array('food', 'amount'), $request, $response)){

		//$reqBody = file_get_contents('php://input');
		//parse_str($reqBody, $request_params);
		$request_data = $request->getParsedBody();

        $food = $request_data['food'];
        $amount = $request_data['amount'];


        //$hash_password = password_hash($password, PASSWORD_DEFAULT);

        $db = new DbOperations;

        $result = $db->createFood($food, $amount);

        if($result == ITEM_CREATED){

            $message = array();
            $message['error'] = false;
            $message['message'] = 'Ingredient created successfully';

            $response->getbody()->write(json_encode($message));

            return $response
                        ->withHeader('Content-type', 'application/json')
                        ->withStatus(201);

        }else if($result == ITEM_FAILURE){

            $message = array();
            $message['error'] = true;
            $message['message'] = 'Some error occurred';

            $response->getbody()->write(json_encode($message));

            return $response
                        ->withHeader('Content-type', 'application/json')
                        ->withStatus(422);

        }else if($result == ITEM_EXISTS){
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


//Employee Database
$app->post('/MyApp/public/createemployee', function(Request $request, Response $response){

    if(!haveEmptyParameters(array('name', 'password', 'position', 'wage'), $request, $response)){

		//$reqBody = file_get_contents('php://input');
		//parse_str($reqBody, $request_params);
		$request_data = $request->getParsedBody();

		$name = $request_data['name'];
		$password = $request_data['password'];
		$position = $request_data['position'];
		$wage = $request_data['wage'];


        $hash_password = password_hash($password, PASSWORD_DEFAULT);

        $db = new DbOperationse;

        $result = $db->createEmployee($name, $hash_password, $position, $wage);

        if($result == ITEM_CREATED){

            $message = array();
            $message['error'] = false;
            $message['message'] = 'Employee created successfully';

            $response->getbody()->write(json_encode($message));

            return $response
                        ->withHeader('Content-type', 'application/json')
                        ->withStatus(201);

        }else if($result == ITEM_FAILURE){

            $message = array();
            $message['error'] = true;
            $message['message'] = 'Some error occurred';

            $response->getbody()->write(json_encode($message));

            return $response
                        ->withHeader('Content-type', 'application/json')
                        ->withStatus(422);

        }else if($result == ITEM_EXISTS){
            $message = array();
            $message['error'] = true;
            $message['message'] = 'Employee Already Exists';

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

//Login Data
$app->post('/MyApp/public/employeelogin', function(Request $request, Response $response){
	if(!haveEmptyParameters(array('name', 'password'), $request, $response)){
		//$reqBody = file_get_contents('php://input');
		//parse_str($reqBody, $request_params);
		$request_data = $request->getParsedBody();

		$name = $request_data['name'];
		$password = $request_data['password'];

		$db = new DbOperationse;

		$result = $db->employeeLogin($name, $password);

		if($result == USER_AUTHENTICATED){

			$emp = $db->getUserByName($name);
			$response_data = array();

			$response_data['error'] = false;
			$response_data['message'] = 'Logged in';
			$response_data['emp'] = $emp;

			$response->getbody()->write(json_encode($response_data));

			return $response
        		->withHeader('Content-type', 'application/json')
				->withStatus(200);

		}else if($result == USER_NOT_FOUND){

			$response_data = array();

			$response_data['error'] = true;
			$response_data['message'] = 'Name not found';

			$response->getbody()->write(json_encode($response_data));

			return $response
        		->withHeader('Content-type', 'application/json')
				->withStatus(200);

		}else if($result == USER_PASSWORD_DO_NOT_MATCH){

			$response_data = array();

			$response_data['error'] = true;
			$response_data['message'] = 'Wrong Password';

			$response->getbody()->write(json_encode($response_data));

			return $response
        		->withHeader('Content-type', 'application/json')
				->withStatus(200);
		}
	}

	return $response
        ->withHeader('Content-type', 'application/json')
		->withStatus(422);
});

// Show all employees
$app->get('/MyApp/public/allemployees', function(Request $request, Response $response){

	$db = new DbOperationse;

	$emps = $db->getAllEmployees();

	$response_data = array();

	$response_data['error'] = false;
	$response_data['emps'] = $emps;

	$response->getbody()->write(json_encode($response_data));

	return $response
        ->withHeader('Content-type', 'application/json')
		->withStatus(200);

});

// Show all ingredients
$app->get('/MyApp/public/allingredients', function(Request $request, Response $response){

	$db = new DbOperations;

	$ingts = $db->getAllIngredients();

	$response_data = array();

	$response_data['error'] = false;
	$response_data['ingts'] = $ingts;

	$response->getbody()->write(json_encode($response_data));

	return $response
        ->withHeader('Content-type', 'application/json')
		->withStatus(200);

});

// Update Ingredients
$app->put('/MyApp/public/updateingredient/{id}', function(Request $request, Response $response, array $args){

	$id = $args['id'];

	if(!haveEmptyParameters(array('food', 'amount'), $request, $response)){

		$reqBody = file_get_contents('php://input');
		parse_str($reqBody, $request_data);
		//$request_data = $request->getParsedBody();
		//$id = $request_data['id'];
		$food = $request_data['food'];
		$amount = $request_data['amount'];
		
		$db = new DbOperations;

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

// Update Employees
$app->put('/MyApp/public/updateemployee/{id}', function(Request $request, Response $response, array $args){

	$id = $args['id'];

	if(!haveEmptyParameters(array('name', 'position', 'wage'), $request, $response)){

		$reqBody = file_get_contents('php://input');
		parse_str($reqBody, $request_data);
		//$request_data = $request->getParsedBody();

		$name = $request_data['name'];
		$position = $request_data['position'];
		$wage = $request_data['wage'];
		
		$db = new DbOperationse;

		if($db->updateEmployees($name, $position, $wage, $id)){
			$response_data = array();
			$response_data['error'] = false;
			$response_data['message'] = 'Update Successful';
			$emp = $db->getUserByName($name);
			$response_data['emp'] = $emp;

			$response->getbody()->write(json_encode($response_data));

			return $response
        		->withHeader('Content-type', 'application/json')
				->withStatus(200);

		}else{
			$response_data = array();
			$response_data['error'] = true;
			$response_data['message'] = 'Try Again';
			$emp = $db->getUserByName($name);
			$response_data['emp'] = $emp;

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

// Update Password
$app->put('/MyApp/public/updatepassword', function(Request $request, Response $response){

	if(!haveEmptyParameters(array('currentpassword', 'newpassword', 'name'), $request, $response)){

		$reqBody = file_get_contents('php://input');
		parse_str($reqBody, $request_data);

		$currentpassword = $request_data['currentpassword'];
		$newpassword = $request_data['newpassword'];
		$name = $request_data['name'];

		$db = new DbOperationse;

		$result = $db->updatePassword($currentpassword, $newpassword, $name);

		if($result == PASSWORD_CHANGED){
			$response_data = array();
			$response_data['error'] = false;
			$response_data['message'] = 'Password Changed';
			$response->getbody()->write(json_encode($response_data));
			return $response->withHeader('Content-type', 'application/json')
							->withStatus(200);
		}else if($result == PASSWORD_DO_NOT_MATCH){
			$response_data = array();
			$response_data['error'] = true;
			$response_data['message'] = 'Password is wrong';
			$response->getbody()->write(json_encode($response_data));
			return $response->withHeader('Content-type', 'application/json')
							->withStatus(200);
		}else if($result == PASSWORD_NOT_CHANGED){
			$response_data = array();
			$response_data['error'] = false;
			$response_data['message'] = 'Error: Password NOT Changed';
			$response->getbody()->write(json_encode($response_data));
			return $response->withHeader('Content-type', 'application/json')
							->withStatus(200);
		}

	}

	return $response
        ->withHeader('Content-type', 'application/json')
		->withStatus(422);

});

// Trash Food
$app->put('/MyApp/public/trashfood/{id}', function(Request $request, Response $response, array $args){
	$id = $args['id'];

	$db = new DbOperations;

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

// Fire Employee
$app->put('/MyApp/public/fireemployee/{id}', function(Request $request, Response $response, array $args){
	$id = $args['id'];

	$db = new DbOperationse;

	$response_data = array();

	if($db->fireEmployee($id)){
		$response_data['error'] = false;
		$response_data['message'] = 'Employee was FIRED!!';
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
	//$request_params = $request->getParsedBody();
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
        $response->getbody()->write(json_encode($error_detail));
        //$response->getbody()->write($error_detail);
    }
    return $error;
}

$app->run();
