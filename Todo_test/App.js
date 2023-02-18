import React, {useState, useEffect} from 'react';
import {
  StyleSheet,
  StatusBar,
  KeyboardAvoidingView,
  Platform,
  Alert,
} from 'react-native';

import {SafeAreaView, SafeAreaProvider} from 'react-native-safe-area-context';
import AddText from './com/addText';
import Todo from './com/todo';
import Title from './com/title';
import todosStorage from './storage/todosStorage';
function App() {
  const [todos, setTodo] = useState([]);

  //불러오기
  useEffect(() => {
    todosStorage.get().then(setTodo).catch(console.error);
  }, []);

  //저장
  useEffect(() => {
    todosStorage.set(todos).catch(console.error);
  }, [todos]);

  //데이터 추가
  const onInsert = text => {
    //todo id 값을 todo 인자에 넣는다. todos.map = todo.id 값을 찾아 새로운 배열로 만든다. 그 중에서 최대값을 찾아 +1을 해줘 새로운 id값을 만듬
    const nextId =
      todos.length > 0 ? Math.max(...todos.map(todo => todo.id)) + 1 : 1;

    //todo = 새로운 아이템 id, text, done을 관리
    const todo = {
      id: nextId,
      text,
      done: false,
      Editable: false,
    };

    setTodo(todos.concat(todo));
  };

  const onToggle = id => {
    const nextTodos = todos.map(todo =>
      todo.id === id ? {...todo, done: !todo.done} : todo,
    );
    setTodo(nextTodos);
  };

  const onDelete = id => {
    const nextTodos = todos.filter(todo => todo.id !== id);
    setTodo(nextTodos);
  };

  const onModify = id => {
    const nextTodos = todos.map(todo =>
      todo.id === id ? {...todo, Editable: !todo.Editable} : todo,
    );
    setTodo(nextTodos);
  };

  return (
    <SafeAreaProvider>
      <SafeAreaView style={styles.backView}>
        <KeyboardAvoidingView style={styles.avoid}>
          <StatusBar backgroundColor="#8b4513" />
          <Title />
          <AddText onInsert={onInsert} />
          <Todo
            todos={todos}
            onToggle={onToggle}
            onDelete={onDelete}
            onModify={onModify}
          />
        </KeyboardAvoidingView>
      </SafeAreaView>
    </SafeAreaProvider>
  );
}
const styles = StyleSheet.create({
  backView: {
    flex: 1,
    backgroundColor: '#8b4513',
  },

  avoid: {
    flex: 1,
  },
});

export default App;