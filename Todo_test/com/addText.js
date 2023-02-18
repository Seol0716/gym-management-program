import React, {useCallback, useRef, useState} from 'react';
import {
  TextInput,
  StyleSheet,
  View,
  TouchableOpacity,
  Keyboard,
  Image,
  Text,
  Alert,
} from 'react-native';

import Toast from 'react-native-toast-message';

function AddText({onInsert}) {
  const [text, setText] = useState('');

  const onPress = () => {
    if (text.trim().length === 0){
      Alert.alert('할 일을 입력해주세요');
    } else {
      onInsert(text);
      setText('');
      Keyboard.dismiss();
    } 
  };

  return (
    <View style={styles.view}>
      <TextInput 
        placeholder="Add Task" 
        style={styles.input} 
        maxLength={15}
        value={text}
        onChangeText={setText}
        returnKeyType="done"
      />

      <TouchableOpacity onPress={onPress}>
        <Image source={require('../com/plus.png')} style={styles.img} />
      </TouchableOpacity>
    </View>
  );
}
const styles = StyleSheet.create({
  view: {
    height: 50,
    justifyContent: 'center',
    flexDirection: 'row',
    paddingHorizontal: 16,
    backgroundColor: '#deb887',
    borderRadius: 20,
    margin: 25,
  },
  input: {
    justifyContent: 'center',
    alignItems: 'center',
    fontSize: 20,
    borderRadius: 15,
    borderColor: '#800000',
    color: '#000000',
    paddingVertical: 8,
    flex: 1,
  },

  img_view: {
    width: 30,
    height: 30,
  },

  img: {
    width: 40,
    height: 40,
    marginLeft: 30,
    marginTop: 5,
  },
});

export default AddText;

