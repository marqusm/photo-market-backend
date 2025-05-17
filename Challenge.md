# Welcome to Photo Market

This is the time to show us your coding and analytical skills. The time limit for this challenge is 7 days. It might be a demanding task, so please complete as much as you can.


## Overview

The coding challenge test your skills on database, API design and implementation. The best approach would be completing the tasks in the given order. Further down this document, there is a list of use cases. Please develop the application based on these requirements.

Photo Market is a photo marketplace where users can upload, showcase and sell their photos. The most liked photos can then be purchased by other users. **Only** the most liked photos are available for purchase by the community.


## Actors

1. **System (SYS)**  
  System processes
2. **Admin user (AD)**  
  Controls the quality of the photos by allowing or not allowing their display on the platform. They can also authorize whether a photo can be sold through the platform.
3. **Photo seller user (PSU)**  
  He/She uploads the photos to be voted and possibly being sold once enough votes are cast. They can also like other photos as well.
4. **Photo buyer user (PBU)**
  This user buys a digital copy of the photo. They can also like other photos as well.


## Use cases

1. PSU uploads a photo. PSU can assign hashtags to the photo.
2. Each photo has to be approved by AD before it is shown on the platform.
3. When adding hashtags to a photo, the top hashtags will be suggested to the PSU.
4. PSU/PBU likes a photo.
5. PSU photo receives minimum amount of likes to be available for purchase, AD approves.
6. PSU photo receives minimum amount of likes to be available for purchase, AD disapproves.
7. PSU can edit/delete their photo from the marketplace.
8. PBU buys photo: When a PBU buys photo, basic information are provided (address, full name etc.).
9. The PBU payment is done with an external provider (e.g. Stripe). 
10. Our SYS stores the transaction ID of the external provider.
11. The payment/transaction information has to be stored to generate reports.
12. The SYS processes the purchase order of a photo to that it can be printed and sent to PBU via mail.


## Tasks

1. Design a Sql or NoSql schema for this platform, considering all use cases
2. Design the API endpoints for the platform
3. Implement an Authentication and Authorization system for the platform (User registration and login)
4. Implement the Entities
5. Implement the API end point to upload a photo
6. Implement the API end point and cache (no libraries please, your own hand-rolled cache) for storing the hashtags of the photos


## Rules

All schema designs can be drawn using a tool (e.g. draw.io, Visio etc.) of your choice, just add it to the repo. You can use Spring stack, but please ensure that the Cache is hand-rolled by yourself and not any custom 3rd party implementation.
