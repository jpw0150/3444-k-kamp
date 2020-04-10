<?php
use Psr\Http\Message\ResponseInterface as Response;
use Psr\Http\Message\ServerRequestInterface as Request;
//use Slim\Handlers\Strategies\RequestResponseArgs;
use Slim\Factory\AppFactory;
require __DIR__ . '/../vendor/autoload.php';

require '../includes/DbOperations.php';

$app = AppFactory::create();

$app->addRoutingMiddleware();
$errorMiddleware = $app->addErrorMiddleware(true, true, true);

$app->add(new Tuupola\Middleware\HttpBasicAuthentication([
    "secure"=>false,
    "users" => [
        "root" => "123456",
    ]
]));


        $app->post('/MyApi/public/createmenu', function(Request $request, Response $response){
    if(!haveEmptyParameters(array('food', 'foodnum'), $request, $response)){

        $request_data = $request->getParsedBody();

        $food = $request_data['food'];
        $foodnum = $request_data['foodnum'];

        $db = new DbOperations;

        $result = $db->createUser($food, $foodnum);

        if($result == USER_CREATED){

            $message = array();
            $message['error'] = false;
            $message['message'] = 'User created successfully';

            $response->getbody()->write(json_encode($message));

             return $response
                        ->withHeader('Content-type', 'application/json')
                        ->withStatus(201);

        }else if($result == USER_FAILURE){

            $message = array();
            $message['error'] = true;
            $message['message'] = 'Error occured';

            $response->getbody()->write(json_encode($message));

            return $response
                        ->withHeader('Content-type', 'application/json')
                        ->withStatus(422);

        }else if($result == USER_EXISTS){

            $message = array();
            $message['error'] = true;
            $message['message'] = 'User Already Created';

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









$app->post('/MyApi/public/createcustomer', function(Request $request, Response $response){

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


$app->post('/MyApi/public/customerlogin', function(Request $request, Response $response){

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

            $response->getBody->write(json_encode($response_data));

            return $response
                ->withHeader('Content-type', 'application/json')
                ->withStatus(200);
        }
    }

    return $response
        ->withHeader('Content-type', 'application/json')
        ->withStatus(422);
});


$app->get('/MyApi/public/allcustomers', function(Request $request, Response $response){

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

$app->put('/MyApi/public/updatecustomer/{id}', function(Request $request, Response $response, array $args){

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

$app->put('/MyApi/public/updatepassword', function(Request $request, Response $response){

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

$app->delete('/MyApi/public/deleteuser/{id}', function(Request $request, Response $response, array $args){
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
        //$response->getBody()->write($error_detail);
    }
    return $error;
}

$app->run();
