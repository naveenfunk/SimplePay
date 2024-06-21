# SimplePay App

## Overview

Welcome to the SimplePay project! This project is designed for candidates to demonstrate their
skills by implementing a simple Eftpos (Electronic Funds Transfer at Point Of Sale) application. The
application has two main functionalities:

1. Performing a transaction
2. Showing the details of the last transaction

### Home Screen

The home screen displays two buttons to trigger the two main functionalities.

### Transaction Screens

The transaction process goes through four screens:

1. **Transaction Info Input Screen**: Enter the transaction amount and type.
2. **Card Info Input Screen**: Enter the card PAN (card number), card expiry year/month, and
   security code.
3. **Processing Screen**: Displays a processing message.
4. **Transaction Result Screen**: Shows the result of the transaction.

After a transaction is completed, the app should navigate back to the home screen.

### Show last transaction screen

The screen displays the details of the last transaction.

> **Note:** Home screen and Transaction screen have been implemented in the initial code.
> Last transaction screen is for the candidate to create.

## General Instructions and Guidelines

- **Project Submission**: Candidates should work on the project at their own pace, but it is
  expected to be submitted within three days after receiving the task.
- **Focus on Quality**: It's not expected to complete all the tasks within the given time frame.
  Focus on demonstrating your understanding, problem-solving skills, and quality of your work.
- **Usage of AI Tools**:
    - You may use AI tools to ask general questions and seek guidance.
    - Pasting code from the project to AI is not allowed.
    - Directly pasting code generated by AI into the project is not allowed.
    - Use the AI for understanding concepts and getting suggestions only.
- **Add TODOs**:
    - Highlight areas that need further improvement or where you have made assumptions.
    - Use `// TODO:` comments in your code to mark these areas.
- **Code Commits**
    - Make frequent commits to the repository to track your progress.

## Getting Started

### Prerequisites

- Android Studio (Jellyfish)
- Java JDK 8 or above
- Kotlin
- git

### Installation

1. Download the project or clone the repository:
   ```sh
   git clone git@github.com:yafei-wang-smartpay/simplePay.git
2. Open the project in Android Studio.
3. Sync the project with Gradle files.
4. Build and run the project on an emulator or a physical device.

> **Important Note** Please do not commit your code to the original repository. Instead,
> create a new repository under your GitHub account and commit your code there. Share the link to your
> repository when you submit your project.

## Tasks and Expectations

This project consists of five main tasks. It is expected that candidates complete at least Task 1
and Task 2. Completing Task 3 and Task 4 is encouraged and will demonstrate a higher level of
proficiency. Task 5 is considered low priority due to its time-consuming nature, but candidates who
are passionate about the challenge are encouraged to attempt it.

> **Note:** It is not expected that candidates attempt to complete all tasks. If you cannot complete
> a task, use TODOs in your code to document future enhancements and improvements.

### Task 1: Fix the Transaction Input Screen

The Transaction Input Screen currently has the following issues:

1. The transaction amount input is not displayed on the screen.
2. The selected transaction type is not displayed.

Your task is to fix these issues and ensure that both the transaction amount and the selected
transaction type are correctly displayed on the screen.

### Task 2: Implement Card Info Validation

In this task, you need to implement validation for the card information entered on the Card Info
Input Screen. The card PAN digit range is from 13 to 19, the CCV is 3 digits, and you need to
validate the year and month input. If the validation fails, navigate to the Transaction Result
screen and display "FAILURE".

#### Steps to Complete:

1. **Validate Card PAN:**
    - Ensure that the card PAN has between 13 to 19 digits.

2. **Validate Expiry Date:**
    - Ensure that the card expiry month is between 1 and 12.
    - Ensure that the card expiry year is the current year or later.

3. **Validate Card Security Code (CCV):**
    - Ensure that the CCV is exactly 3 digits.

4. **Handle Validation Failures:**
    - If any validation fails, navigate to the Transaction Result screen and display "FAILURE".

### Task 3: Store the Transaction Data locally

In the initial code, the transaction data is not stored. Your task is to implement the functionality
to store transaction data locally. You can choose to use Room DB, file, or other suitable libraries,
or
simply store it in the memory. .

#### Steps to Complete:

1. **Choose a Storage Method:**
    - You can use Room DB, file, or any other library to store transaction data.
    - Or simply store it in the memory.

2. **Implement Data Storage:**
    - Ensure that the transaction data is stored correctly when a transaction is processed.
    - Store necessary details such as transaction amount, transaction type, and other relevant
      information.

### Task 4: Implement the Show Last Transaction Feature

After implementing the transaction data storage, you need to retrieve and display the transaction
record. When the user presses the "Show Last Transaction" button on the home screen, it should
navigate to a new screen displaying the last transaction details. The details should include the
transaction amount, transaction type, last 6 digits of the card PAN, and transaction result.

#### Steps to Complete:

1. **Create a New Screen:**
    - Design a new screen to display the last transaction details.

2. **Retrieve Transaction Data:**
    - Implement logic to retrieve the last transaction from storage or from memory when navigating
      to the new screen.

3. **Display Transaction Details:**
    - Display the transaction amount, transaction type, last 6 digits of the card PAN, and
      transaction result.

4. **Update Navigation:**
    - Ensure that pressing the "Show Last Transaction" button on the home screen navigates to the
      new screen.

### Task 5: Implement Sending the Transaction Request to a REST API

In this task, you need to implement the functionality to send a transaction request to a REST API
and handle the response. You can use libraries like Retrofit, Ktor, or any other suitable library to
achieve this.

#### Steps to Complete:

1. **Set Up Retrofit or Ktor:**
    - Choose a library (Retrofit, Ktor, etc.) and set it up in your project.

2. **Define API Endpoints:**
    - Define the REST API endpoints for sending transaction requests and receiving responses.

3. **Send Transaction Request:**
    - Implement the functionality to send a transaction request with the necessary data.

4. **Handle API Response:**
    - Handle the response from the API and update the UI accordingly.

5. **Use a Mock Server for Testing:**
    - Use a mock server to simulate API responses during development and testing.

#### Mock Server Suggestions:

Here are some mock servers you can use for testing your API interactions:

- **[MockServer](https://www.mock-server.com/)**: A powerful tool for mocking and testing APIs.
- **[Mockoon](https://mockoon.com/)**: A simple and easy-to-use tool for creating mock APIs.
- **[Postman Mock Server](https://learning.postman.com/docs/designing-and-developing-your-api/mocking-data/overview/)**: A feature of Postman that allows you to create mock servers and simulate API responses.

## Contact

If you have any questions or need further clarification, please contact yafei.wang@smartpay.co.nz.
