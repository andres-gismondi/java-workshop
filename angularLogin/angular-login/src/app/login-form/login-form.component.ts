import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { Validators } from '@angular/forms';
import { FormArray } from '@angular/forms';
import { HttpClient, HttpParams, HttpHeaders, HttpResponse } from '@angular/common/http';

//Service
import { ApiService } from '../api.service';
import { headersToString } from 'selenium-webdriver/http';

@Component({
  selector: 'app-login-form',
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.css']
})
export class LoginFormComponent implements OnInit {

  private body = new HttpParams()
    .set('userName', 'julian@julian.com.ar')
    .set('password', 'julian1234');


  headers1: String[];
  constructor(
    private http: HttpClient,
    private fb: FormBuilder,
    private apiSerive: ApiService) { }

  ngOnInit() {
    //console.log(this.apiSerive.getUsers());
    this.getUsers();
    this.login();

  }

  getUsers(): void {
    this.http.get('http://localhost:8080/grupo23_war_exploded/user-controller/users/2', {
      headers: { 'token': '2-123456' }
    })
      .subscribe(data => { console.log(data) });
  }

  login() {
    let headers = new HttpHeaders({
      'Content-Type':"application/json"
    });
    console.log(headers)
    this.http.post<any>('http://localhost:8080/grupo23_war_exploded/user-controller/login',null, {headers:headers, observe:'response'})
      .subscribe(head => {/*console.log(head.headers.get('Authorization'))*/ console.log('Error -> '+JSON.stringify(head.headers.get('Authorization')))})
      
  }

}