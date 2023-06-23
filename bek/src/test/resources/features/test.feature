Feature: calculation test

  @sum  @Before
  Scenario: test addition
    * i execute endpoint is "addition" with num1 is "1000" in 10 and num2 is "2000" in 10
    * the client receives status code of 200
    * the client receives result is "3000.0"

  @sum  @Before
  Scenario: test addition with binary numbers
    * i execute endpoint is "addition" with num1 is "1000" in 2 and num2 is "1001" in 2
    * the client receives status code of 200
    * the client receives result is "17.0"

  @sum  @Before
  Scenario: test addition with hex and binary numbers
    * i execute endpoint is "addition" with num1 is "152" in 16 and num2 is "11" in 2
    * the client receives status code of 200
    * the client receives result is "341.0"

  @division  @Before
  Scenario: test division with hex and binary numbers
    * i execute endpoint is "division" with num1 is "150" in 16 and num2 is "11" in 2
    * the client receives status code of 200
    * the client receives result is "112.0"

  @multiplication  @Before
  Scenario: test multiplication with hex and binary numbers
    * i execute endpoint is "multiplication" with num1 is "152" in 16 and num2 is "11" in 2
    * the client receives status code of 200
    * the client receives result is "1014.0"

  @subtraction  @Before
  Scenario: test subtraction with hex and binary numbers
    * i execute endpoint is "subtraction" with num1 is "152" in 16 and num2 is "11" in 2
    * the client receives status code of 200
    * the client receives result is "335.0"

  @sum  @Before
  Scenario Outline: test addition with parameters
    * i execute endpoint is "addition" with num1 is <num1> in <sys1> and num2 is <num2> in <sys2>
    * the client receives status code of 200
    Examples:
      | num1    | num2       | sys1 | sys2|
      | "1001010" | "131313" | 2    | 8   |
      | "202202"  | "330303" | 8    | 8   |
      | "3320202" | "20202"  | 8    | 8   |

  Scenario: test get parameters with class
    * test get parameters for class

      | num1    | num2   |
      | 1001010 | 131313 |
      | 202202  | 330303 |
      | 3320202 | 20202  |

  @subtraction  @Before
  Scenario: test get parameters with class sub
    * test get parameters for class for subtraction

      | num1    | num2   |
      | 1001010 | 131313 |
      | 202202  | 330303 |
      | 3320202 | 20202  |

  @subtraction  @Before
  Scenario: test subtraction with hex and binary numbers with custom dec
    * i execute endpoint is subtraction with "152;11"
    * the client receives status code of 200
    * the client receives result is "335.0"

  @subtraction  @Before
  Scenario: test subtraction with hex and binary numbers with date
    * i execute endpoint is subtraction with "152;011"
    * the date create is 2022-12-22

