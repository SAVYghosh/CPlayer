import { AppPage } from './app.po';
import { browser, logging, element, protractor, by } from 'protractor';

describe('CPlayer-frontend App', () => {
  let page: AppPage;

  beforeEach(() => {
    page = new AppPage();
  });

  it('should display title', () => {
    page.navigateTo();
    expect(browser.getTitle()).toEqual('CPlayer');
  });

  it('should be redirected to /login route on opening the application', () => {
    expect(browser.getCurrentUrl()).toContain('/login');
  });

  it('should be redirected to /register route', () => {
    browser.element(by.css('.register-button')).click();
    expect(browser.getCurrentUrl()).toContain('/register');
  });

  it('should be able to register user', () => {
    browser.element(by.id('firstName')).sendKeys('usera');
    browser.element(by.id('lastName')).sendKeys('lastUser');
    browser.element(by.id('userId')).sendKeys('sourav1');
    browser.element(by.id('password')).sendKeys('sourav1');
    browser.element(by.css('.register-user')).click();
    expect(browser.getCurrentUrl()).toContain('/login');
  });
  

  it('should be able to login user and navigate to 1st page', () => {
    browser.element(by.id('userId')).sendKeys('sourav1');
    browser.element(by.id('password')).sendKeys('sourav1');
    browser.element(by.css('.login-user')).click();
    expect(browser.getCurrentUrl()).toContain('/players/search');
  });

  it('should be able to search player', () => {
    browser.element(by.id('search')).click();
    expect(browser.getCurrentUrl()).toContain('/players/search');
    browser.element(by.id('search-button-input')).sendKeys('sourav');
    browser.element(by.id('search-button-input')).sendKeys(protractor.Key.ENTER);
    const searchItems = element.all(by.id('player-name'));
    expect(searchItems.count()).toBe(4);
    for(let i = 0; i < 1; i += 1) {
      expect(searchItems.get(i).getText()).toContain('Sourav Ganguly');
    }
  });

  it('should be able to add palyer to favourite', async() => {
    browser.driver.manage().window().maximize();
    browser.driver.sleep(1000);
    const searchItems = element.all(by.css('.player-thumbnail'));
    expect(searchItems.count()).toBe(4);
    searchItems.get(0).click();
    browser.element(by.id('add-button')).click();
    browser.driver.sleep(5000);
  });

  it('should be able to show details palyer', async() => {
    browser.element(by.id('favlist')).click();
    expect(browser.getCurrentUrl()).toContain('/players/FavList');
    browser.element(by.id('details')).click();
    browser.driver.sleep(10000);

    browser.element(by.id('back')).click();
    browser.element(by.id('favlist')).click();

  });

  it('should be able to delete palyer to favourite', async() => {
    browser.element(by.id('favlist')).click();
    expect(browser.getCurrentUrl()).toContain('/players/FavList');
    browser.element(by.id('delete')).click();
    const delItem = element.all(by.id('player-name'));
    expect(delItem.count()).toBe(0);
  });

});

