import {TodoPage} from './todo-list.po';
import {browser, protractor} from 'protractor';

let origFn = browser.driver.controlFlow().execute;

//https://hassantariqblog.wordpress.com/2015/11/09/reduce-speed-of-angular-e2e-protractor-tests/
browser.driver.controlFlow().execute = function () {
  let args = arguments;

  // queue 100ms wait between test
  //This delay is only put here so that you can watch the browser do its' thing.
  //If you're tired of it taking long you can remove this call
  origFn.call(browser.driver.controlFlow(), function () {
    return protractor.promise.delayed(100);
  });

  return origFn.apply(browser.driver.controlFlow(), args);
};

describe('Todo list', () => {
  let page: TodoPage;

  beforeEach(() => {
    page = new TodoPage();
  });

  it('should get and highlight Todo Name attribute ', () => {
    page.navigateTo();
    expect(page.getTodoTitle()).toEqual('Todos');
  });

  it('should type something in filter owner box and check that it returned correct element', () => {
    page.navigateTo();
    page.typeAnOwner("l");
    expect(page.getUniqueTodo("588959851e4dc2da5e75599b")).toEqual("Blanche");
    page.backspace();
    page.typeAnOwner("Barry");
    expect(page.getUniqueTodo("588959852fa0cc5a7059c277")).toEqual("Barry");
  });

  // it('should click on the age 27 times and return 3 elements', () => {
  //   page.navigateTo();
  //   page.getTodoByCategory('homework');
  //
  //
  //
  //   expect(page.getUniqueTodo("588959859fb68af109fb7939")).toEqual("Dawn");
  //
  //   expect(page.getUniqueTodo("58895985bb003400a5431e77")).toEqual("Fry");
  //
  // });
});
