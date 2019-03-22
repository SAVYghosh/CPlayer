import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';

import { Location } from '@angular/common';
import { RegisterComponent } from './register.component';
import { AuthenticationService } from '../../authentication.service';
import { Router } from '@angular/router';
import { Observable,of } from 'rxjs';
import { FormsModule } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material';
import { MatInputModule } from '@angular/material';
import { MatButtonModule } from '@angular/material/button';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HttpClientModule } from '@angular/common/http';
import { By } from '@angular/platform-browser';



const testConfig = {
  userData: {
    firstName: 'test',
    lastName: 'testLast',
    userId: 'testUser',
    password: 'testPass'
  }
}

describe('Register Component', () => {
  let component: RegisterComponent;
  let fixture: ComponentFixture<RegisterComponent>;
  let authService: AuthenticationService;
  let spyUser: any;
  let routes: Router;
  let location: Location;

  class AuthServiceStub {
    currentUser: any;
    
    constructor() {

     }

    register(credentials) {
      if(credentials.userId == testConfig.userData.userId) {
        console.log('data:::', this.currentUser);
        return of(credentials.userId);
      } else {
       return of(false);
      }
    }
  }

  class dummy {

  }

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RegisterComponent ],
      imports: [FormsModule, MatFormFieldModule, MatInputModule,
        MatButtonModule, BrowserAnimationsModule, HttpClientModule,
        RouterTestingModule.withRoutes(
          [{path: '', component: dummy}]
        )
      ],
      providers: [{provide: AuthenticationService, useClass: AuthServiceStub}]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    routes = TestBed.get(Router);
    fixture = TestBed.createComponent(RegisterComponent);
    location = TestBed.get(Location);
    component = fixture.componentInstance;
    fixture.detectChanges();
    fixture.debugElement.injector.get(AuthenticationService);
  });

  it('should create app component', async(() => {
    const app = fixture.debugElement.componentInstance;
    expect(app).toBeTruthy();
  }));


});
